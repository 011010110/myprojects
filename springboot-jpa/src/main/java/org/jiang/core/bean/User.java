package org.jiang.core.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description TODO
 * @Author li.linhua
 * @Date 2019/12/27
 * @Version 1.0
 */
@Entity
@Table(name="sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy="uuid")
    private String id;

    /**
     * 用户名
     */
    @Column(name = "user_name",length = 64)
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password",length = 64)
    private String password;

    /**
     * 盐值
     */
    @Column(name = "salt",length = 64)
    private String salt;

    /**
     * 电话号码
     */
    @Column(name = "phone",length = 64)
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email",length = 64)
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
