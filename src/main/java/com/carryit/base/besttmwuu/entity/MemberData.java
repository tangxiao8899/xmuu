package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class MemberData implements Serializable {

    private int uid;  //用户id
    private String nickName;//昵称
    private String city; //城市
    private String autograph; //签名
    private String avatar; //头像地址
    private String openid;  //微信OPENID
    private String realname; //真是姓名
    private double credit2; //账户余额
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public double getCredit2() {
        return credit2;
    }

    public void setCredit2(double credit2) {
        this.credit2 = credit2;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
