package com.carryit.base.besttmwuu.entity;

public class imsEweiShopCreditshopGoodsWithBLOBs extends imsEweiShopCreditshopGoods {
    private String detail;

    private String showlevels;

    private String buylevels;

    private String showgroups;

    private String buygroups;

    private String subdetail;

    private String noticedetail;

    private String goodsdetail;

    private String storeids;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getShowlevels() {
        return showlevels;
    }

    public void setShowlevels(String showlevels) {
        this.showlevels = showlevels == null ? null : showlevels.trim();
    }

    public String getBuylevels() {
        return buylevels;
    }

    public void setBuylevels(String buylevels) {
        this.buylevels = buylevels == null ? null : buylevels.trim();
    }

    public String getShowgroups() {
        return showgroups;
    }

    public void setShowgroups(String showgroups) {
        this.showgroups = showgroups == null ? null : showgroups.trim();
    }

    public String getBuygroups() {
        return buygroups;
    }

    public void setBuygroups(String buygroups) {
        this.buygroups = buygroups == null ? null : buygroups.trim();
    }

    public String getSubdetail() {
        return subdetail;
    }

    public void setSubdetail(String subdetail) {
        this.subdetail = subdetail == null ? null : subdetail.trim();
    }

    public String getNoticedetail() {
        return noticedetail;
    }

    public void setNoticedetail(String noticedetail) {
        this.noticedetail = noticedetail == null ? null : noticedetail.trim();
    }

    public String getGoodsdetail() {
        return goodsdetail;
    }

    public void setGoodsdetail(String goodsdetail) {
        this.goodsdetail = goodsdetail == null ? null : goodsdetail.trim();
    }

    public String getStoreids() {
        return storeids;
    }

    public void setStoreids(String storeids) {
        this.storeids = storeids == null ? null : storeids.trim();
    }
}