package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class Member implements Serializable{

    private Integer id;

    private Integer uid;

    private String avatar;//头像

    private String realName;

    private String city;//城市

    private String nickName;//昵称

    private String level;

    private String levelName;//圈子等级表

    private float credit2;//账户余额

    private float credit1;//账户积分

    private Integer zhuquanzi;

    private String autograph;

    private String createtime;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getZhuquanzi() {
        return zhuquanzi;
    }

    public void setZhuquanzi(Integer zhuquanzi) {
        this.zhuquanzi = zhuquanzi;
    }

    public float getCredit2() {
        return credit2;
    }

    public void setCredit2(float credit2) {
        this.credit2 = credit2;
    }

    public float getCredit1() {
        return credit1;
    }

    public void setCredit1(float credit1) {
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
