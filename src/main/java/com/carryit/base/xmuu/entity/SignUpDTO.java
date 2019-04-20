package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class SignUpDTO implements Serializable {

    private Integer uid; //用户i
    private String nickName;//昵称
    private String avatar;//头像

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
