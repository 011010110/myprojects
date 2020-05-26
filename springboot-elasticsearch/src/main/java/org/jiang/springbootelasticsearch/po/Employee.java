package org.jiang.springbootelasticsearch.po;

/**
 * @Description
 * @Author li.linhua
 * @Date 2020/4/29
 * @Version 1.0
 */
public class Employee {

    private String name;
    private String sex;
    private String age;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
