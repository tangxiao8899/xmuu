package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.util.List;

public class FriendResp implements Serializable {
    private int duration;
    private List<String> data;
    private int count;
    private String action;
    private String uri;
    private long timestamp;
    private List<Object> entities;
    private List<FriendRespUser> friendRespUser;

    public List<FriendRespUser> getFriendRespUser() {
        return friendRespUser;
    }

    public void setFriendRespUser(List<FriendRespUser> friendRespUser) {
        this.friendRespUser = friendRespUser;
    }

    public List<Object> getEntities() {
        return entities;
    }

    public void setEntities(List<Object> entities) {
        this.entities = entities;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
