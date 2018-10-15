package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
/*
*发布活动
 */
public class Activity implements Serializable{

    private Integer uid; //用户id
    private Integer bid; //圈子id
    private long startTime;//活动开始时间，13位毫秒值
    private long endTime;//活动结束时间，13位毫秒值
    private long cerateTime;//创建时间，13位毫秒值
    private String address;//活动详细地址
    private float cost; //费用(免费为0.00)
    private int peopleNumber;//人数
    private int level;//参加人员等级限制
    private String detail;//活动介绍
    private String images;

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
