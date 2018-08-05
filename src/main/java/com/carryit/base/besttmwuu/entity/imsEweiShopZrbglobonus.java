package com.carryit.base.besttmwuu.entity;

import java.math.BigDecimal;

public class imsEweiShopZrbglobonus {
    private Integer id;

    private Integer uniacid;

    private String openid;

    private Integer uid;

    private Byte level;

    private Integer globonus;

    private BigDecimal globonusmoney;

    private Integer createtime;

    private Double rate;

    private String province;

    private String city;

    private Boolean type;

    private Integer qid;

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Integer getGlobonus() {
        return globonus;
    }

    public void setGlobonus(Integer globonus) {
        this.globonus = globonus;
    }

    public BigDecimal getGlobonusmoney() {
        return globonusmoney;
    }

    public void setGlobonusmoney(BigDecimal globonusmoney) {
        this.globonusmoney = globonusmoney;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }
}