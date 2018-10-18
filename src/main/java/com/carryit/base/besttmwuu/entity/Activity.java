package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
/*
*发布活动
 */
public class Activity implements Serializable{

    private Integer uid; //用户id
    private Integer bid; //圈子id
    private String title;//活动标题
    private long startTime;//活动开始时间，13位毫秒值
    private long endTime;//活动结束时间，13位毫秒值
    private long cerateTime;//创建时间，13位毫秒值
    private String address;//活动详细地址
    private float cost; //费用(免费为0.00)
    private int peopleNumber;//人数
    private int level;//参加人员等级限制
    private String detail;//活动介绍
    private String images;
    private String board名称;//圈子
    private String levelname;//等级名称
    private String joinNumber; //参加人数

    public String getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(String joinNumber) {
        this.joinNumber = joinNumber;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBoard名称() {
        return board名称;
    }

    public void setBoard名称(String board名称) {
        this.board名称 = board名称;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getCerateTime() {
        return cerateTime;
    }

    public void setCerateTime(long cerateTime) {
        this.cerateTime = cerateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
