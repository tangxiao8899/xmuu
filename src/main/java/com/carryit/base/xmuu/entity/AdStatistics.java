package com.carryit.base.xmuu.entity;

import java.io.Serializable;
import java.util.Date;

public class AdStatistics implements Serializable {

    private String id;
    private Date adStatisticsDate;
    private Double adStatisticsMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getAdStatisticsDate() {
        return adStatisticsDate;
    }

    public void setAdStatisticsDate(Date adStatisticsDate) {
        this.adStatisticsDate = adStatisticsDate;
    }

    public Double getAdStatisticsMoney() {
        return adStatisticsMoney;
    }

    public void setAdStatisticsMoney(Double adStatisticsMoney) {
        this.adStatisticsMoney = adStatisticsMoney;
    }
}
