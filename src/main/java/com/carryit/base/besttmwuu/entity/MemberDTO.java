package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class MemberDTO implements Serializable {

    private String nickName; //昵称
    private String levelName; //会员等级
    private String corporateName; //公司名字
    private String sincerity; //诚信值
    private String phone; //电话
    private String mailbox; //邮箱
    private String address;//公司地址
    private String speciality;//公司简介
    private String banner;

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

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getSincerity() {
        return sincerity;
    }

    public void setSincerity(String sincerity) {
        this.sincerity = sincerity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
