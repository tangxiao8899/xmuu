package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.util.Date;

public class Globouns implements Serializable{

    private Integer id; //id 主键

    private Integer uniacid;

    private String openId;

    private Integer uid;

    private Integer level;//股东 等级 1:联合股东 2:常务股东 3:A级股东 4:B级股东 5:C级股东 6:期权股东 7：常务股东

    private Integer globonus;//股权数

    private double globonusmoney;// 股价

    private Date createtime;//成为股东时间

    private double rate;//股票增长率

    private String province;//地区

    private String city;//城市

    private Integer type;//1:旧版 2:新版

    private Integer qid;//圈子ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUniacid() {
        return uniacid;
    }

    public void setUniacid(Integer uniacid) {
        this.uniacid = uniacid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getGlobonus() {
        return globonus;
    }

    public void setGlobonus(Integer globonus) {
        this.globonus = globonus;
    }

    public double getGlobonusmoney() {
        return globonusmoney;
    }

    public void setGlobonusmoney(double globonusmoney) {
        this.globonusmoney = globonusmoney;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }
}
