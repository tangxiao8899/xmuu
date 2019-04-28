package com.carryit.base.xmuu.entity;

import java.io.Serializable;
import java.util.Date;

public class AdProfit implements Serializable {

    private String id;
    private String adFrom;
    private String adProfit;
    private Date adTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdFrom() {
        return adFrom;
    }

    public void setAdFrom(String adFrom) {
        this.adFrom = adFrom;
    }

    public String getAdProfit() {
        return adProfit;
    }

    public void setAdProfit(String adProfit) {
        this.adProfit = adProfit;
    }

    public Date getAdTime() {
        return adTime;
    }

    public void setAdTime(Date adTime) {
        this.adTime = adTime;
    }
}
