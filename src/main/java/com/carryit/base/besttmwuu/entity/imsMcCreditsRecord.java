package com.carryit.base.besttmwuu.entity;

import java.math.BigDecimal;

public class imsMcCreditsRecord {
    private Integer id;

    private Integer uid;

    private Integer uniacid;

    private String credittype;

    private BigDecimal num;

    private Integer operator;

    private Integer createtime;

    private String remark;

    private String module;

    private Integer clerkId;

    private Integer storeId;

    private Byte clerkType;

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

    public Integer getUniacid() {
        return uniacid;
    }

    public void setUniacid(Integer uniacid) {
        this.uniacid = uniacid;
    }

    public String getCredittype() {
        return credittype;
    }

    public void setCredittype(String credittype) {
        this.credittype = credittype == null ? null : credittype.trim();
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public Integer getClerkId() {
        return clerkId;
    }

    public void setClerkId(Integer clerkId) {
        this.clerkId = clerkId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Byte getClerkType() {
        return clerkType;
    }

    public void setClerkType(Byte clerkType) {
        this.clerkType = clerkType;
    }
}