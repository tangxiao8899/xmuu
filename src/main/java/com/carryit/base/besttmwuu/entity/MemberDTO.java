package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class MemberDTO implements Serializable {
    private Integer uid;
    private String autograph;//个性签名
    private String nickName; //昵称
    private String levelName; //会员等级
    private String corporateName; //公司名字
    private String sincerity; //诚信值
    private String phone; //电话
    private String mailbox; //邮箱
    private String address;//公司地址
    private String companyProfile;//公司简介
    private String banner;
    private String friends;
    private Integer hidePhone;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getHidePhone() {
        return hidePhone;
    }

    public void setHidePhone(Integer hidePhone) {
        this.hidePhone = hidePhone;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
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

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
