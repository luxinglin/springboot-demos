package cn.com.gary.springboot.data.mongo.api;

import cn.com.gary.springboot.data.mongo.common.repository.MongoDbHelper;
import cn.com.gary.springboot.data.mongo.common.util.AnsibleFactsUtil;
import cn.com.gary.springboot.data.mongo.model.mongo.PhysicalConfigItem;
import cn.com.gary.springboot.data.mongo.model.pojo.User;
import cn.pioneer.dcim.saas.common.pojo.PageQuery;
import cn.pioneer.dcim.saas.common.pojo.PageResult;
import cn.pioneer.dcim.saas.common.pojo.RestResult;
import cn.pioneer.dcim.saas.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: TODO
 * @create 2019-04-19 14:24
 **/
@RestController
@RequestMapping("/v1/configs")
public class ConfigItemController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigItemController.class);

    @Autowired
    MongoDbHelper physicalConfigItemMongoDbHelper;

    @PostMapping
    public RestResult createPhysicalConfigItem(@RequestBody PhysicalConfigItem configItem) {
        LOGGER.info("createPhysicalConfigItem");
        configItem = AnsibleFactsUtil.convertAnsibleFactsJson(JsonUtil.toJson(configItem.getDetails()));

        //校验重复
        PhysicalConfigItem persist = (PhysicalConfigItem) physicalConfigItemMongoDbHelper.findOne(Criteria.where("uuid").is(configItem.getUuid()));
        if (persist != null) {
            return new RestResult("已经存在配置，不做新增处理");
        }

        //set new id
        configItem.setId(Long.valueOf(physicalConfigItemMongoDbHelper.getNextId(PhysicalConfigItem.class.getName())));
        physicalConfigItemMongoDbHelper.saveOrUpdate(configItem);
        return new RestResult("新建配置项目成功");
    }

    @PutMapping
    public RestResult updateUserName(@RequestBody User user) {
        LOGGER.info("updateUserName");
//        Map<String, Object> updateMap = new HashMap<>();
//        // 查询name为itdragon-1的数据，将name修改为ITDragonBlog
//        User persist = (User) userMongoHelper.findOne(Criteria.where("id").is(user.getId()));
//        if (null == persist) {
//            LOGGER.error("User non-existent");
//            return new RestResult("修改失败，记录不存在");
//        }
//        updateMap.put("id", persist.getId());
//        updateMap.put("name", user.getName());
//        userMongoHelper.update(updateMap);

        return new RestResult("用户名修改成功");
    }

    @PutMapping("/address")
    public RestResult updateUserAddress(@RequestBody User user) {
        LOGGER.info("updateUserAddress");
//        Map<String, Object> updateMap = new HashMap<>();
//        User persist = (User) userMongoHelper.findOne(Criteria.where("id").is(user.getId()));
//        if (null == persist) {
//            LOGGER.info("User non-existent");
//            return new RestResult("修改失败，记录不存在");
//        }
//        Location address = new Location();
//        address.setId(Long.valueOf(userMongoHelper.getNextId(Location.class.getName())));
//        address.setProvince(user.getAddress().getProvince());
//        address.setCity(user.getAddress().getCity());
//        updateMap.put("id", persist.getId());
//        updateMap.put("address", address);
//        userMongoHelper.update(updateMap);

        return new RestResult("地址修改成功");
    }

    @PutMapping("/ability")
    public RestResult updateUserAbility(@RequestBody User user) {
        LOGGER.info("updateUserAbility");
//        Map<String, Object> updateMap = new HashMap<>();
//        User perisist = (User) userMongoHelper.findOne(Criteria.where("id").is(user.getId()));
//        if (null == perisist) {
//            LOGGER.info("User non-existent");
//            return new RestResult("修改失败，记录不存在");
//        }
//        List<String> abilitys = perisist.getAbility();
//        user.getAbility().forEach(item -> {
//            abilitys.add(item.toString());
//        });
//        updateMap.put("id", perisist.getId());
//        updateMap.put("ability", abilitys);
//        userMongoHelper.update(updateMap);

        return new RestResult("能力值修改成功");
    }

    @GetMapping
    public PageResult pagePhysicalConfigItem(Map<String, Object> params) {
        LOGGER.info("pagePhysicalConfigItem");
        PageQuery pageQuery = new PageQuery(params);

        // 排序
        physicalConfigItemMongoDbHelper.setOrderDescField("id");

        // 查询id小于于100的数据
        PageResult pageResult = physicalConfigItemMongoDbHelper.page(Criteria.where("id").lt(100), pageQuery.getPagesize(), pageQuery.getPagenum());

        return pageResult;
    }
}
