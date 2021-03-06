package cn.com.gary.springboot.data.mongo.common.repository;

import cn.com.gary.springboot.data.mongo.common.pojo.QueryParam;
import cn.pioneer.dcim.saas.common.exception.BizException;
import cn.pioneer.dcim.saas.common.pojo.PageResult;
import cn.pioneer.dcim.saas.common.util.ToyUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class MongoDbHelper {

    private static final String ID = "id";
    private static final String MONGODB_ID = "_id";
    /**
     * Spring Data提供支持MongoDB的方法
     */
    @Autowired(required = false)
    private MongoTemplate mongoTemplate;
    /**
     * 实体类
     */
    private Class entityClass;
    /**
     * 数据库表名
     */
    private String collectionName;
    /**
     * 升序字段
     */
    private String orderAscField;
    /**
     * 降序字段
     */
    private String orderDescField;

    public MongoDbHelper() {
    }

    public MongoDbHelper(Class entityClass) {
        this.entityClass = entityClass;
        this.collectionName = _getCollectionName();
    }

    public MongoDbHelper(Class entityClass, String collectionName) {
        this.entityClass = entityClass;
        this.collectionName = collectionName;
    }

    /**
     * @param requestArgs Map，无需自带ID
     * @return
     * @Title save
     * @Description 通过Map创建实体类
     */
    public Boolean save(Map<String, Object> requestArgs) {
        try {
            Object object = getEntityClass().newInstance();
            if (null == requestArgs.get(ID)) {
                requestArgs.put(ID, getNextId());
            }
            BeanUtils.populate(object, requestArgs);
            saveOrUpdate(object);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }

        return Boolean.valueOf(true);
    }

    /**
     * @param requestArgs
     * @return
     */
    public Boolean saveBatch(List<Map<String, Object>> requestArgs) {
        if (ToyUtil.listEmpty(requestArgs)) {
            return false;
        }
        try {
            List<Object> items = new ArrayList<>();
            for (Map<String, Object> requestArg : requestArgs) {
                Object object = getEntityClass().newInstance();
                if (null == requestArg.get(ID)) {
                    requestArg.put(ID, getNextId());
                }
                BeanUtils.populate(object, requestArg);
                items.add(object);
            }
            mongoTemplate.insertAll(items);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }

        return Boolean.valueOf(true);
    }


    /**
     * @param object 实体类，需自带ID
     * @return
     * @Title save
     * @Description 通过对象创建实体类
     */
    public Boolean saveOrUpdate(Object object) {
        try {
            this.mongoTemplate.save(object, this.collectionName);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    /**
     * @param object 实体类，需要自带id
     * @return
     */
    public boolean remove(Object object) {
        try {
            this.mongoTemplate.remove(object, this.collectionName);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * @param requestArgs Map，需自带ID， 形如：{id: idValue, name: nameValue, ....}
     * @return
     * @Title update
     * @Description 通过Map更新实体类具体字段，可以减少更新出错字段，执行的销率更高，需严格要求数据结构的正确性
     */
    public Boolean update(Map<String, Object> requestArgs) {
        Object id = requestArgs.get(ID);
        if (null == id) {
            return Boolean.valueOf(false);
        }
        try {
            Update updateObj = new Update();
            requestArgs.remove(ID);
            for (String key : requestArgs.keySet()) {
                updateObj.set(key, requestArgs.get(key));
            }
            findAndModify(Criteria.where(ID).is(id), updateObj);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    /**
     * @param criteria 查询条件
     * @return
     * @Title find
     * @Description 根据查询条件返回所有数据，不推荐
     */
    public List find(Criteria criteria) {
        Query query = new Query(criteria);
        _sort(query);
        return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
    }

    /**
     * @param criteria 查询条件
     * @param pageSize 查询数量
     * @return
     * @Title find
     * @Description 根据查询条件返回指定数量数据
     */
    public List find(Criteria criteria, Integer pageSize) {
        Query query = new Query(criteria).limit(pageSize.intValue());
        _sort(query);
        return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
    }

    /**
     * @param criteria   查询条件
     * @param pageSize   查询数量
     * @param pageNumber 当前页数
     * @return
     * @Title find
     * @Description 根据查询条件分页返回指定数量数据
     */
    public List find(Criteria criteria, Integer pageSize, Integer pageNumber) {
        Query query = new Query(criteria).skip((pageNumber.intValue() - 1) * pageSize.intValue()).limit(pageSize.intValue());
        _sort(query);
        return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
    }

    /**
     * 分页查询
     *
     * @param criteria
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public PageResult page(Criteria criteria, Integer pageSize, Integer pageNumber) {
        Query query = new Query(criteria).skip((pageNumber.intValue() - 1) * pageSize.intValue()).limit(pageSize.intValue());
        _sort(query);
        long total = this.mongoTemplate.count(query, this.entityClass);
        List data = this.mongoTemplate.find(query, this.entityClass, this.collectionName);
        PageResult pageResult = new PageResult(total, data);
        return pageResult;
    }

    public Object findAndModify(Criteria criteria, Update update) {
        // 第一个参数是查询条件，第二个参数是需要更新的字段，第三个参数是需要更新的对象，第四个参数是MongoDB数据库中的表名
        return this.mongoTemplate.findAndModify(new Query(criteria), update, this.entityClass, this.collectionName);
    }

    /**
     * @param queryParams
     * @return
     */
    private DBObject constructQuery(QueryParam... queryParams) {
        DBObject query = new BasicDBObject();
        Arrays.asList(queryParams).stream().forEach(p -> {
            String operator = p.getOperator();
            String fieldName = p.getFieldName();
            Object value = p.getValue();
            switch (operator) {
                case "EQ":
                    query.put(fieldName, new BasicDBObject("$eq", value));
                    break;
                case "NE":
                    query.put(fieldName, new BasicDBObject("$ne", value));
                    break;
                case "GT":
                    query.put(fieldName, new BasicDBObject("$gt", value));
                    break;
                case "GTE":
                    query.put(fieldName, new BasicDBObject("$gte", value));
                    break;
                case "LT":
                    query.put(fieldName, new BasicDBObject("$lt", value));
                    break;
                case "LTE":
                    query.put(fieldName, new BasicDBObject("$lte", value));
                    break;
                case "IN":
                    query.put(fieldName, new BasicDBObject("$in", value));
                    break;
                case "NIN":
                    query.put(fieldName, new BasicDBObject("$nin", value));
                    break;
                default:
                    break;
            }
        });

        return query;
    }

    /**
     * 条件查询
     *
     * @param queryParams 查询条件
     * @return
     * @throws BizException
     */
    public List<DBObject> search(QueryParam... queryParams) throws BizException {
        try {
            DBCollection collection = mongoTemplate.getCollection(collectionName);
            List<DBObject> result = new ArrayList<>();
            DBObject query = constructQuery(queryParams);
            DBCursor dbCursor = collection.find(query);
            while (dbCursor.hasNext()) {
                result.add(dbCursor.next());
            }
            return result;
        } catch (Exception e) {
            throw new BizException(e);
        }
    }

    public void createIndex(List<String> fields) {
        if(ToyUtil.listEmpty(fields)){
            return;
        }

        DBCollection collection = mongoTemplate.getCollection(collectionName);
        DBObject keys = new BasicDBObject();
        fields.forEach(field->{
            ((BasicDBObject) keys).append(field,1);
        });
        collection.createIndex(keys);
    }

    /**
     * @param id 实体类ID
     * @return
     * @Title findById
     * @Description 通过ID查询数据
     */
    public Object findById(Object id) {
        return this.mongoTemplate.findById(id, this.entityClass, this.collectionName);
    }

    /**
     * @param criteria 实体类ID
     * @return
     * @Title findOne
     * @Description 通过查询条件返回一条数据
     */
    public Object findOne(Criteria criteria) {
        Query query = new Query(criteria).limit(1);
        _sort(query);
        return this.mongoTemplate.findOne(query, this.entityClass, this.collectionName);
    }

    /**
     * ID 自动增长
     *
     * @return
     */
    public String getNextId() {
        return getNextId(getCollectionName());
    }

    public String getNextId(String seq_name) {
        String sequence_collection = "seq";
        String sequence_field = "seq";
        DBCollection seq = this.mongoTemplate.getCollection(sequence_collection);
        DBObject query = new BasicDBObject();
        query.put(MONGODB_ID, seq_name);
        DBObject change = new BasicDBObject(sequence_field, Integer.valueOf(1));
        DBObject update = new BasicDBObject("$inc", change);
        DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
        return res.get(sequence_field).toString();
    }

    private void _sort(Query query) {
        if (null != this.orderAscField) {
            String[] fields = this.orderAscField.split(",");
            for (String field : fields) {
                if (ID.equals(field)) {
                    field = MONGODB_ID;
                }
                query.with(new Sort(Sort.Direction.ASC, new String[]{field}));
            }
        } else {
            if (null == this.orderDescField) {
                return;
            }
            String[] fields = this.orderDescField.split(",");
            for (String field : fields) {
                if (ID.equals(field)) {
                    field = MONGODB_ID;
                }
                query.with(new Sort(Sort.Direction.DESC, new String[]{field}));
            }
        }
    }

    /**
     * 获取Mongodb数据库中的表名，若表名不是实体类首字母小写，则会影响后续操作
     */
    private String _getCollectionName() {
        String className = this.entityClass.getName();
        Integer lastIndex = Integer.valueOf(className.lastIndexOf("."));
        className = className.substring(lastIndex.intValue() + 1);
        return StringUtils.uncapitalize(className);
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getOrderAscField() {
        return orderAscField;
    }

    public void setOrderAscField(String orderAscField) {
        this.orderAscField = orderAscField;
    }

    public String getOrderDescField() {
        return orderDescField;
    }

    public void setOrderDescField(String orderDescField) {
        this.orderDescField = orderDescField;
    }

}