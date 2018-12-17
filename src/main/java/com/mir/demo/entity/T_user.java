package com.mir.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "t_user")
public class T_user implements Serializable{

    @Id
    private String id;
    @Column(name = "username")
    private String userName;
    @Column
    private  String mobile;
    @Column
    private  String password;
    @Column
    private Date createe_time;
    @Column
    private int price;

    public T_user() {
    }

    @Override
    public String toString() {
        return "T_user{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", createe_time=" + createe_time +
                ", price=" + price +
                '}';
    }

    public T_user(String id, String userName, String mobile, String password, Date createe_time, int price) {
        this.id = id;
        this.userName = userName;
        this.mobile = mobile;
        this.password = password;
        this.createe_time = createe_time;
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatee_time() {
        return createe_time;
    }

    public void setCreatee_time(Date createe_time) {
        this.createe_time = createe_time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
