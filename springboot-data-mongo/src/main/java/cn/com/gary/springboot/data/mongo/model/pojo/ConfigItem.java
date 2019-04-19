package cn.com.gary.springboot.data.mongo.model.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: TODO
 * @create 2019-04-19 16:15
 **/
public abstract class ConfigItem implements Serializable {
    /**
     * CI主键
     */
    @Id
    private Long id;
    /**
     * CI名称
     */
    private String name;
    /**
     * CI编码
     */
    private String barCode;
    /**
     * 分类编码
     */
    private String categoryCode;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 图标
     */
    private List<String> logos;
    /**
     * 创建时间
     */
    private Date createDateTime;
    /**
     * 更新时间
     */
    private Date updateDateTime;
    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 持有者
     */
    private User owner;

    /**
     * 更多信息
     */
    private Map<String, Object> details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getLogos() {
        return logos;
    }

    public void setLogos(List<String> logos) {
        this.logos = logos;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
