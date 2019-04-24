package cn.com.gary.springboot.data.mongo.service;

import cn.com.gary.springboot.data.mongo.common.repository.MongoDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: TODO
 * @create 2019-04-23 14:53
 **/
@Service
public class ResInstService {
    @Autowired
    MongoDbHelper configItemMongoDbHelper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> selectAllResInst() {
        String sql = "select res_id as resId, COMPANY_ID AS companyId,RES_NAME as name,RES_CODE as barCode,res_key as uuid,data_source as dataSource from res_inst ";

        return jdbcTemplate.queryForList(sql);
    }

    /**
     *
     */
    public void syncToMongodb() {
        List<Map<String, Object>> list = selectAllResInst();
        configItemMongoDbHelper.saveBatch(list);
    }

}
