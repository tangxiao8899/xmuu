package com.carryit.base.xmuu.entity;

import java.io.Serializable;
import java.util.Date;

public class MerchantProfit implements Serializable {

    private String id;
    private String merchantId;
    private String merchantName;
    private String city;
    private Double merchantProfit;
    private Date profitDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getMerchantProfit() {
        return merchantProfit;
    }

    public void setMerchantProfit(Double merchantProfit) {
        this.merchantProfit = merchantProfit;
    }

    public Date getProfitDate() {
        return profitDate;
    }

    public void setProfitDate(Date profitDate) {
        this.profitDate = profitDate;
    }
}
