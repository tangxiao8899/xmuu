package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class Follow implements Serializable {

    private int uid;
    private int bid;
    private String follow;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }
}
