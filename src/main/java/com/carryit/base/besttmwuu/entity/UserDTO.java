package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String realName;  //名字
    private String profilePhoto; //头像
    private String WealthValue; //财富值
    private String sincerity; //诚信值
    private Integer seq;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSincerity() {
        return sincerity;
    }

    public void setSincerity(String sincerity) {
        this.sincerity = sincerity;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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
