package com.carryit.base.besttmwuu.entity;

public class imsEweiShopMerchReg {
    private Integer id;

    private Integer uniacid;

    private String openid;

    private String merchname;

    private String salecate;

    private String desc;

    private String realname;

    private String mobile;

    private Byte status;

    private Integer applytime;

    private String uname;

    private String upass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUniacid() {
        return uniacid;
    }

    public void setUniacid(Integer uniacid) {
        this.uniacid = uniacid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getMerchname() {
        return merchname;
    }

    public void setMerchname(String merchname) {
        this.merchname = merchname == null ? null : merchname.trim();
    }

    public String getSalecate() {
        return salecate;
    }

    public void setSalecate(String salecate) {
        this.salecate = salecate == null ? null : salecate.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getApplytime() {
        return applytime;
    }

    public void setApplytime(Integer applytime) {
        this.applytime = applytime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass == null ? null : upass.trim();
    }
}