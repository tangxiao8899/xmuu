package com.carryit.base.xmuu.entity;

import java.io.Serializable;
import java.util.Date;

public class MerchantProfitStatistics implements Serializable {

    private String id;
    private String city;
    private Double profit;
    private Date profitDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Date getProfitDate() {
        return profitDate;
    }

    public void setProfitDate(Date profitDate) {
        this.profitDate = profitDate;
    }
}
