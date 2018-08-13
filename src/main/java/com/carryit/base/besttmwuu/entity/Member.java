package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class Member implements Serializable{

    private Integer id;

    private String avatar;//头像

    private String realName;//昵称

    private String city;//城市

    private String nickName;//个性签名



    private String levelName;//圈子等级表

    private String credit2;//账户余额

    private String credit1;//账户积分

    public String getCredit2() {
        return credit2;
    }

    public void setCredit2(String credit2) {
        this.credit2 = credit2;
    }

    public String getCredit1() {
        return credit1;
    }

    public void setCredit1(String credit1) {
        this.credit1 = credit1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
