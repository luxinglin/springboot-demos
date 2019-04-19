package cn.com.gary.springboot.data.mongo.common.config;

import cn.com.gary.springboot.data.mongo.common.repository.MongoDbHelper;
import cn.com.gary.springboot.data.mongo.model.mongo.NonPhysicalConfigItem;
import cn.com.gary.springboot.data.mongo.model.mongo.PhysicalConfigItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: Mongodb 数据访问配置类
 * @create 2019-04-19 14:24
 **/
@Configuration
public class MongodbBeansConfig {

    /**
     * 物理类CI数据存取类
     *
     * @return
     */
    @Bean
    public MongoDbHelper physicalConfigItemMongoDbHelper() {
        return new MongoDbHelper(PhysicalConfigItem.class);
    }

    /**
     * 非物理CI数据存取类
     *
     * @return
     */
    @Bean
    public MongoDbHelper nonPhysicalConfigItemMongoDbHelper() {
        return new MongoDbHelper(NonPhysicalConfigItem.class);
    }

}