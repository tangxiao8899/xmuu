package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer uid;

    private String userName;    //姓名

    private String salt;//盐值

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String password;   //密码

    private Integer age;   //年龄

    private String idCard;  //身份证

    private String phone;   //电话

    private String address;   //公司地址

    private String companyProfile; //公司简介

    private String corporateName;//公司名称

    private String need;   //近期需求

    private String education; //学历

    private String mailbox;//公司邮箱

    private String marriage;//婚否

    private String services;//提供服务

    private String sex;//性别

    private int bid;

    private Integer hidePhone;//隐藏手机号码

    private Integer hideSincerity;//隐藏诚信值

    private String iCode;//邀请码

    public Integer getHidePhone() {
        return hidePhone;
    }

    public void setHidePhone(Integer hidePhone) {
        this.hidePhone = hidePhone;
    }

    public Integer getHideSincerity() {
        return hideSincerity;
    }

    public void setHideSincerity(Integer hideSincerity) {
        this.hideSincerity = hideSincerity;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
