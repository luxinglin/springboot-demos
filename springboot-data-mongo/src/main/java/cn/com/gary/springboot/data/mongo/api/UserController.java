//package cn.com.gary.springboot.data.mongo.api;
//
//import cn.com.gary.springboot.data.mongo.model.pojo.ConfigItem;
//import cn.com.gary.springboot.data.mongo.model.pojo.Location;
//import cn.com.gary.springboot.data.mongo.model.pojo.User;
//import cn.com.gary.springboot.data.mongo.common.repository.MongoDbHelper;
//import cn.pioneer.dcim.saas.common.pojo.PageQuery;
//import cn.pioneer.dcim.saas.common.pojo.PageResult;
//import cn.pioneer.dcim.saas.common.pojo.RestResult;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author luxinglin
// * @version 1.0
// * @Description: TODO
// * @create 2019-04-19 14:24
// **/
//@RestController
//@RequestMapping("/v1/users")
//public class UserController {
//    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//
//    @Autowired
//    MongoDbHelper configItemMongoDbHelper;
//
//    @PostMapping
//    public RestResult createConfigItem(@RequestBody ConfigItem configItem) {
//        LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^createUser");
//        // 插入25条数据
//        for (int i = 0; i < 25; i++) {
//            User inner = new User();
//            inner.setId(Long.valueOf(userMongoHelper.getNextId(User.class.getName())));
//            inner.setAge(25 + i);
//            inner.setName("testName-" + i);
//            Location address = new Location();
//            address.setId(Long.valueOf(userMongoHelper.getNextId(Location.class.getName())));
//            address.setProvince(user.getAddress().getProvince());
//            address.setCity(user.getAddress().getCity());
//            inner.setAddress(address);
//            ArrayList<String> ability = new ArrayList<>();
//            ability.add("Java");
//            inner.setAbility(ability);
//            userMongoHelper.saveOrUpdate(inner);
//            LOGGER.info("user : " + user.toString());
//        }
//
//        return new RestResult("新建成功");
//    }
//
//    @PutMapping
//    public RestResult updateUserName(@RequestBody User user) {
//        LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^updateUserName");
//        Map<String, Object> updateMap = new HashMap<>();
//        // 查询name为itdragon-1的数据，将name修改为ITDragonBlog
//        User persist = (User) userMongoHelper.findOne(Criteria.where("id").is(user.getId()));
//        if (null == persist) {
//            LOGGER.error("^^^^^^^^^^^^^^^^^^^^^^User non-existent");
//            return new RestResult("修改失败，记录不存在");
//        }
//        updateMap.put("id", persist.getId());
//        updateMap.put("name", user.getName());
//        userMongoHelper.update(updateMap);
//
//        return new RestResult("用户名修改成功");
//    }
//
//    @PutMapping("/address")
//    public RestResult updateUserAddress(@RequestBody User user) {
//        LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^updateUserAddress");
//        Map<String, Object> updateMap = new HashMap<>();
//        User persist = (User) userMongoHelper.findOne(Criteria.where("id").is(user.getId()));
//        if (null == persist) {
//            LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^User non-existent");
//            return new RestResult("修改失败，记录不存在");
//        }
//        Location address = new Location();
//        address.setId(Long.valueOf(userMongoHelper.getNextId(Location.class.getName())));
//        address.setProvince(user.getAddress().getProvince());
//        address.setCity(user.getAddress().getCity());
//        updateMap.put("id", persist.getId());
//        updateMap.put("address", address);
//        userMongoHelper.update(updateMap);
//
//        return new RestResult("地址修改成功");
//    }
//
//    @PutMapping("/ability")
//    public RestResult updateUserAbility(@RequestBody User user) {
//        LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^updateUserAbility");
//        Map<String, Object> updateMap = new HashMap<>();
//        User perisist = (User) userMongoHelper.findOne(Criteria.where("id").is(user.getId()));
//        if (null == perisist) {
//            LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^User non-existent");
//            return new RestResult("修改失败，记录不存在");
//        }
//        List<String> abilitys = perisist.getAbility();
//        user.getAbility().forEach(item -> {
//            abilitys.add(item.toString());
//        });
//        updateMap.put("id", perisist.getId());
//        updateMap.put("ability", abilitys);
//        userMongoHelper.update(updateMap);
//
//        return new RestResult("能力值修改成功");
//    }
//
//    @GetMapping
//    public PageResult findUserPage(Map<String, Object> params) {
//        LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^findUserPage");
//        PageQuery pageQuery = new PageQuery(params);
//        // 排序
//        userMongoHelper.setOrderDescField("asc");
//
//        // 查询age大于25的数据
//        PageResult pageResult = userMongoHelper.page(Criteria.where("age").gt(25), pageQuery.getPagesize(), pageQuery.getPagenum());
//
//        return pageResult;
//    }
//}
