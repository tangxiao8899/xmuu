package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private Integer uid; //用户id
    private String nickName;  //名字
    private String profilePhoto; //头像
    private String WealthValue; //财富值
    private int sincerity; //诚信值
    private Integer seq;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getSincerity() {
        return sincerity;
    }

    public void setSincerity(int sincerity) {
        this.sincerity = sincerity;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getWealthValue() {
        return WealthValue;
    }

    public void setWealthValue(String wealthValue) {
        WealthValue = wealthValue;
    }
}
