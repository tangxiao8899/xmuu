package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.util.List;

public class Board implements Serializable {

    private Integer id;
    private Integer cid;
    private String title; //圈子名字
    private String logo; //logo地址
    private String banner; //banner图地址
    private int topic; //话题数
    private int concerns;//关注数
    private Boolean follow; //是否关注
    private List<String> bannerList;//图片集合
    private int memberNumber;
    private String advertisement; //banner图地址
    private String culturewall;//文化墙

    public String getCulturewall() {
        return culturewall;
    }

    public void setCulturewall(String culturewall) {
        this.culturewall = culturewall;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public int getConcerns() {
        return concerns;
    }

    public void setConcerns(int concerns) {
        this.concerns = concerns;
    }

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }

    public List<String> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<String> bannerList) {
        this.bannerList = bannerList;
    }
}
