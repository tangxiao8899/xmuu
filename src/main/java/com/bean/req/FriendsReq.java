package com.bean.req;

import com.bean.CommonRequest;

import java.util.Date;

public class FriendsReq extends CommonRequest {

    private int id;
    private int uid; //用户ID
    private String nickName; //昵称
    private String avatar; //用户头像
    private Date processingTime; //申请好友时间
    private Date withTime; //申请通过时间
    private int state; //状态  0：拒绝  1：通过  2：待审核
    public String getToAddUsername() {
        return toAddUsername;
    }

    public void setToAddUsername(String toAddUsername) {
        this.toAddUsername = toAddUsername;
    }

    private String toAddUsername; //待添加的好友账号

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Date processingTime) {
        this.processingTime = processingTime;
    }

    public Date getWithTime() {
        return withTime;
    }

    public void setWithTime(Date withTime) {
        this.withTime = withTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
