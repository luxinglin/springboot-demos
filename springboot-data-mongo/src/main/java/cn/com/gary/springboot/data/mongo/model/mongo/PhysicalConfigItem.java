package cn.com.gary.springboot.data.mongo.model.mongo;

import cn.com.gary.springboot.data.mongo.model.pojo.ConfigItem;
import cn.com.gary.springboot.data.mongo.model.pojo.Location;

import java.util.List;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: TODO
 * @create 2019-04-19 16:13
 **/
public class PhysicalConfigItem extends ConfigItem {

    private String ips;

    private String os;

    private String memory;

    private String cpuNum;

    private String uuid;
    /**
     * 存放位置
     */
    private Location location;
    /**
     * 关联的其他CI
     */
    private List<ConfigItem> releatedConfigItems;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<ConfigItem> getReleatedConfigItems() {
        return releatedConfigItems;
    }

    public void setReleatedConfigItems(List<ConfigItem> releatedConfigItems) {
        this.releatedConfigItems = releatedConfigItems;
    }
}
