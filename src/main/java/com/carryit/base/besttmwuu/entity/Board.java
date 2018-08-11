package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class Board implements Serializable {

    private Integer id;
    private Integer cid;
    private String title; //圈子名字
    private String logo; //logo地址
    private String banner; //banner图地址
    private Integer topic; //话题数
    private Integer concerns;//关注数
    private Boolean follow; //是否关注

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
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

    public Integer getTopic() {
        return topic;
    }

    public void setTopic(Integer topic) {
        this.topic = topic;
    }

    public Integer getConcerns() {
        return concerns;
    }

    public void setConcerns(Integer concerns) {
        this.concerns = concerns;
    }
}
