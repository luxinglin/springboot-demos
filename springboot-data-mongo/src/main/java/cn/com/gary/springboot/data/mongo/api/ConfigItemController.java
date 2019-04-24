package cn.com.gary.springboot.data.mongo.api;

import cn.com.gary.springboot.data.mongo.common.pojo.QueryParam;
import cn.com.gary.springboot.data.mongo.common.util.AnsibleFactsUtil;
import cn.com.gary.springboot.data.mongo.model.pojo.ConfigItem;
import cn.com.gary.springboot.data.mongo.service.ConfigItemService;
import cn.pioneer.dcim.saas.common.pojo.PageQuery;
import cn.pioneer.dcim.saas.common.pojo.PageResult;
import cn.pioneer.dcim.saas.common.pojo.RestResult;
import cn.pioneer.dcim.saas.common.util.JsonUtil;
import cn.pioneer.dcim.saas.common.util.ToyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    ConfigItemService configItemService;

    @PostMapping
    public RestResult createConfigItem(@RequestBody ConfigItem configItem) {
        LOGGER.info("createConfigItem");
//        ConfigItem configItem = AnsibleFactsUtil.convertAnsibleFactsJson(map);


        configItemService.saveOrUpdate(configItem);
        return new RestResult("新建配置项目成功");
    }

    /**
     * @return
     */
    @PostMapping("/sync")
    public RestResult syncMysql2Mongo() {
        LOGGER.info("syncMysql2Mongo");
        configItemService.syncToMongodb();
        return new RestResult("同步mysql数据到mongodb中成功");
    }

    @DeleteMapping("/{id}")
    public RestResult removeConfigItem(@PathVariable Integer id) {
        LOGGER.info("removeConfigItem");

        configItemService.remove(id);

        return new RestResult("删除配置项成功");
    }


    @GetMapping
    public PageResult pageConfigItem(Map<String, Object> params) {
        LOGGER.info("pageConfigItem");
        PageQuery pageQuery = new PageQuery(params);
        return configItemService.pageConfigItem(pageQuery);
    }

    @GetMapping("/adv")
    public RestResult advSearch(QueryParam queryParams) {
        LOGGER.info("advSearch");

        // 查询id小于于100的数据
        List result = configItemService.search(queryParams);

        return new RestResult(result);
    }
}
