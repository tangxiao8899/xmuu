package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.sql.Date;
import java.math.BigInteger;
public class Order implements Serializable {
    private Integer id;

    private Integer uid;//用户ID

    private String ordersn;//订单号

    private Double price;//价格

    private Integer status;//状态-1取消状态0付款待发货1已付款3付款成功 2:待付款

    private Integer paytype;//1余额支付，2在线支付，3货到付款

    private String transid;//微信支付单号

    private Long createtime;//创建时间java.math.BigInteger

    private String level;

    private int bid;

    private int aid;

    private String name;//姓名
    private String phone;
    private String sex;//(0女，1男)
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    private String paysource;//3商城购买，4充值---来源

    public String getPaysource() {
        return paysource;
    }

    public void setPaysource(String paysource) {
        this.paysource = paysource;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }


}
