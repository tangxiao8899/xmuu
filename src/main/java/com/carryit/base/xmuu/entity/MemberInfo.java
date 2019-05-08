package com.carryit.base.xmuu.entity;

import java.io.Serializable;

public class MemberInfo  implements Serializable {
    private Integer uid;


    private String nickname;//昵称

    private String mailbox;//邮箱

    private String idCord;//身份证

    private String address;//地址

    private String education;//学历

    private String marriage;//婚姻状态

    private String autograph;//个性签名

    private String avatar;//头像

    private String city;//城市

    private String need;//近期需求

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getIdCord() {
        return idCord;
    }

    public void setIdCord(String idCord) {
        this.idCord = idCord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
