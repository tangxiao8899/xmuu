package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.sql.Date;
import java.math.BigInteger;
public class Order implements Serializable {
    private Integer id;

    private Integer uid;//用户ID

    private String ordersn;//订单号

    private long price;//价格

    private Integer status;//状态-1取消状态0付款待发货1已付款3付款成功 2:待付款

    private Integer paytype;//1余额支付，2在线支付，3货到付款

    private String transid;//微信支付单号

    private Long createtime;//创建时间java.math.BigInteger

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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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
