package com.carryit.base.besttmwuu.entity;

import java.util.Date;

/**
 * 账户资金流水实体
 */
public class ImsUserCapitalFlowEntity {
    private Integer id;
    private Integer uid; //用户ID
    private Long price; //变更金额
    private int type;  //变更类型  0：收入  1：支出
    private int source; //资金来源  0：充值  1：打赏
    private Date createTime; //创建时间

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
