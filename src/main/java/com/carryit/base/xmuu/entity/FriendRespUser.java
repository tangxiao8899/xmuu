package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class FriendRespUser implements Serializable {
    private Integer uid;
    private String avatar;//头像
    private String nickName;//昵称
    private String phone; //电话

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
