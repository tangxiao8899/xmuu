package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.util.List;

public class BoardDetail implements Serializable {
    private Board board;   //圈子信息
    private String follow; //"0"未关注,1关注
    private long topic;    //话题数
    private long followCount; //关注数
    private List<String> advertisement;//广告集合

    public List<String> getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(List<String> advertisement) {
        this.advertisement = advertisement;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public long getTopic() {
        return topic;
    }

    public void setTopic(long topic) {
        this.topic = topic;
    }

    public long getFollowCount() {
        return followCount;
    }

    public void setFollowCount(long followCount) {
        this.followCount = followCount;
    }
}
