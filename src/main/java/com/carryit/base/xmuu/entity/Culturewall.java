package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class Culturewall implements Serializable {

    private String culturewall;//文化墙
    private Integer bid;

    public String getCulturewall() {
        return culturewall;
    }

    public void setCulturewall(String culturewall) {
        this.culturewall = culturewall;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
}
