package com.carryit.base.besttmwuu.entity;

import java.math.BigDecimal;

public class imsEweiShopMemberLevel {
    private Integer id;

    private Integer uniacid;

    private Integer level;

    private String levelname;

    private BigDecimal ordermoney;

    private Integer ordercount;

    private BigDecimal discount;

    private Byte enabled;

    private Boolean enabledadd;

    private Boolean buygoods;

    private Integer childrennum;

    private String upgoods;

    private BigDecimal percent;

    private Integer totalpeople;

    private BigDecimal teampercent;

    private String goodsids;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname == null ? null : levelname.trim();
    }

    public BigDecimal getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(BigDecimal ordermoney) {
        this.ordermoney = ordermoney;
    }

    public Integer getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(Integer ordercount) {
        this.ordercount = ordercount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabledadd() {
        return enabledadd;
    }

    public void setEnabledadd(Boolean enabledadd) {
        this.enabledadd = enabledadd;
    }

    public Boolean getBuygoods() {
        return buygoods;
    }

    public void setBuygoods(Boolean buygoods) {
        this.buygoods = buygoods;
    }

    public Integer getChildrennum() {
        return childrennum;
    }

    public void setChildrennum(Integer childrennum) {
        this.childrennum = childrennum;
    }

    public String getUpgoods() {
        return upgoods;
    }

    public void setUpgoods(String upgoods) {
        this.upgoods = upgoods == null ? null : upgoods.trim();
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Integer getTotalpeople() {
        return totalpeople;
    }

    public void setTotalpeople(Integer totalpeople) {
        this.totalpeople = totalpeople;
    }

    public BigDecimal getTeampercent() {
        return teampercent;
    }

    public void setTeampercent(BigDecimal teampercent) {
        this.teampercent = teampercent;
    }

    public String getGoodsids() {
        return goodsids;
    }

    public void setGoodsids(String goodsids) {
        this.goodsids = goodsids == null ? null : goodsids.trim();
    }
}