package cn.com.gary.springboot.data.mongo.model.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: TODO
 * @create 2019-04-19 14:22
 **/
public class User implements Serializable {
    @Id
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 邮件
     */
    private String email;
    /**
     * 微信号
     */
    private String weChat;

    /**
     * 直属经理
     */
    private User manager;
    /**
     * 下属雇员
     */
    private List<User> employees;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }
}