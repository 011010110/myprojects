package org.jiang.core.bean;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author li.linhua
 * @Date 2019/12/27
 * @Version 1.0
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户名
     */

    private String userName;

    /**
     * 密码
     */

    private String password;

    /**
     * 盐值
     */

    private String salt;

    /**
     * 电话号码
     */

    private String phone;

    /**
     * 邮箱
     */

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName=" + userName +
                ", password=" + password +
                ", salt=" + salt +
                ", phone=" + phone +
                ", email=" + email +
                "}";
    }
}
