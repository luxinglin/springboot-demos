package cn.com.gary.springboot.data.mongo.common.util;

import cn.com.gary.springboot.data.mongo.model.pojo.ConfigItem;
import cn.pioneer.dcim.saas.common.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luxinglin
 * @version 1.0
 * @Description:
 * @create 2019-04-19 16:54
 **/
public class AnsibleFactsUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnsibleFactsUtil.class);

    private AnsibleFactsUtil() {
    }

    /**
     * @param map
     * @return
     */
    public static ConfigItem convertAnsibleFactsJson(Map<String, Object> map) {
        if (map == null) {
            return null;
        }

//        String json = JsonUtil.toJson(map.get("ansible_facts"));
//        ConfigItem configItem = new ConfigItem();
//        JsonNode jsonNode = JsonUtil.fromJson(json);
//
//        configItem.setName(jsonNode.get("ansible_hostname").asText());
//        configItem.setMore(JsonUtil.fromJson(json, HashMap.class));
//        //其他属性构造
//        ///configItem.put("ips", jsonNode.get("ansible_all_ipv4_addresses").asText());
//        ///configItem.put("memory", jsonNode.get("ansible_memtotal_mb").asText());
//        ///configItem.put("os", jsonNode.get("ansible_os_family").asText());
//        ///configItem.put("cpuNum", jsonNode.get("ansible_processor_count").asText());
//        configItem.setResKey(jsonNode.get("ansible_product_uuid").asText());

        return null;
    }
}
