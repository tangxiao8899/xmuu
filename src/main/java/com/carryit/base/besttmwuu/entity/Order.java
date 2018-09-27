package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.sql.Date;
import java.math.BigInteger;
public class Order implements Serializable {
    private Integer id;

    private Integer uid;//用户ID

    private String ordesn;//订单号

    private Float price;//价格

    private Integer status;//状态-1取消状态0付款待发货1已付款3付款成功

    private Integer paytype;//1余额支付，2在线支付，3货到付款

    private String transid;//微信支付单号

    private BigInteger createtime;//创建时间java.math.BigInteger

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

    public String getOrdesn() {
        return ordesn;
    }

    public void setOrdesn(String ordesn) {
        this.ordesn = ordesn;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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

    public BigInteger getCreatetime() {
        return createtime;
    }

    public void setCreatetime(BigInteger createtime) {
        this.createtime = createtime;
    }
}
