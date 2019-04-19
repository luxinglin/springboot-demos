package cn.com.gary.springboot.data.mongo.common.util;

import cn.com.gary.springboot.data.mongo.model.mongo.PhysicalConfigItem;
import cn.pioneer.dcim.saas.common.util.JsonUtil;
import cn.pioneer.dcim.saas.common.util.ToyUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

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
     * @param json
     * @return
     */
    public static PhysicalConfigItem convertAnsibleFactsJson(String json) {
        if (ToyUtil.nullOrEmpty(json)) {
            return null;
        }

        PhysicalConfigItem configItem = new PhysicalConfigItem();
        JsonNode jsonNode = JsonUtil.fromJson(json).get("ansible_facts");
        configItem.setIps(jsonNode.get("ansible_all_ipv4_addresses").asText());
        configItem.setMemory(jsonNode.get("ansible_memtotal_mb").asText());
        configItem.setOs(jsonNode.get("ansible_os_family").asText());
        configItem.setName(jsonNode.get("ansible_hostname").asText());
        configItem.setCpuNum(jsonNode.get("ansible_processor_count").asText());
        configItem.setUuid(jsonNode.get("ansible_product_uuid").asText());

        configItem.setDetails(JsonUtil.fromJson(json, HashMap.class));

        return configItem;
    }
}
