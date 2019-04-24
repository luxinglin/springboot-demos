package cn.com.gary.springboot.data.mongo.service;

import cn.com.gary.springboot.data.mongo.common.pojo.QueryParam;
import cn.com.gary.springboot.data.mongo.common.repository.MongoDbHelper;
import cn.com.gary.springboot.data.mongo.model.pojo.ConfigItem;
import cn.pioneer.dcim.saas.common.pojo.PageQuery;
import cn.pioneer.dcim.saas.common.pojo.PageResult;
import cn.pioneer.dcim.saas.common.util.EntityUtils;
import cn.pioneer.dcim.saas.common.util.ToyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: TODO
 * @create 2019-04-24 15:01
 **/
@Service
public class ConfigItemService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigItemService.class);
    @Autowired
    MongoDbHelper configItemMongoDbHelper;
    @Autowired
    ResInstService resInstService;

    /**
     * @param configItem
     */
    public void saveOrUpdate(ConfigItem configItem) {
        if (configItem == null) {
            return;
        }

        if (ToyUtil.notEmpty(configItem.getResKey())) {
            //校验重复
            ConfigItem persist = this.findOne(Criteria.where("uuid").is(configItem.getResKey()));
            if (persist != null) {
                configItem.setId(persist.getId());
                LOGGER.warn("数据记录已存在，将做更新操作");
            }
        }

        if (EntityUtils.isPKNullOrEmpty(configItem.getId())) {
            configItem.setId(Integer.valueOf(configItemMongoDbHelper.getNextId(ConfigItem.class.getName())));
        }
        configItemMongoDbHelper.saveOrUpdate(configItem);
    }

    public ConfigItem findOne(Criteria criteria) {
        return (ConfigItem) configItemMongoDbHelper.findOne(criteria);
    }

    public void syncToMongodb() {
        resInstService.syncToMongodb();
    }

    public void remove(Integer id) {
        //校验重复
        ConfigItem persist = this.findOne(Criteria.where("id").is(id));
        if (persist == null) {
            return;
        }
        configItemMongoDbHelper.remove(persist);
    }

    public PageResult pageConfigItem(PageQuery pageQuery) {
        // 排序
        configItemMongoDbHelper.setOrderDescField("id");

        // 查询id小于于100的数据
        PageResult pageResult = configItemMongoDbHelper.page(Criteria.where("id").lt(10000L), pageQuery.getPagesize(), pageQuery.getPagenum());

        return pageResult;
    }

    public List search(QueryParam queryParams) {
        return configItemMongoDbHelper.search(queryParams);
    }
}
