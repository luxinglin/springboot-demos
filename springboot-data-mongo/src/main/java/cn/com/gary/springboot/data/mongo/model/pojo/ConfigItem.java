package cn.com.gary.springboot.data.mongo.model.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: TODO
 * @create 2019-04-19 16:15
 **/
public class ConfigItem implements Serializable {
    /**
     * CI主键
     */
    @Id
    private Integer id;
    /**
     * MySQL中关联资源ID
     */
    private Long resId;

    /**
     * 资源唯一键
     */
    private String resourceId;
    /**
     * CI名称
     */
    private String name;
    /**
     * 分类编码
     */
    private String resCategoryCode;
    /**
     * 位置信息
     */
    private Location location;
    /**
     * 关联其他配置列表
     */
    private List<ConfigItem> relatedConfigItems;

    private Map<String, Object> more;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResCategoryCode() {
        return resCategoryCode;
    }

    public void setResCategoryCode(String resCategoryCode) {
        this.resCategoryCode = resCategoryCode;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<ConfigItem> getRelatedConfigItems() {
        return relatedConfigItems;
    }

    public void setRelatedConfigItems(List<ConfigItem> relatedConfigItems) {
        this.relatedConfigItems = relatedConfigItems;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Map<String, Object> getMore() {
        return more;
    }
}
