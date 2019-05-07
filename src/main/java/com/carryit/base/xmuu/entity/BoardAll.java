package com.carryit.base.xmuu.entity;

import com.bean.CommonRequest;

public class BoardAll extends CommonRequest {
    private String logo;

    private String title;

    private String culturewall;

    private String updateTime;

    private Integer uid;

    private Integer bid; //圈子ID

    private String nickname;

    private String avatar;

    private Integer level;

    private Integer zhuquanzi;

    public Integer getFoucus() {
        return foucus;
    }

    public void setFoucus(Integer foucus) {
        this.foucus = foucus;
    }

    private Integer foucus;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Integer pageSize;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCulturewall() {
        return culturewall;
    }

    public void setCulturewall(String culturewall) {
        this.culturewall = culturewall;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getZhuquanzi() {
        return zhuquanzi;
    }

    public void setZhuquanzi(Integer zhuquanzi) {
        this.zhuquanzi = zhuquanzi;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
}
