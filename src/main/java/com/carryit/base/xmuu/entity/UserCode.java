package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class UserCode implements Serializable {
    private Integer uid;
    private String iCode;
    private int pageStart;
    private int pageSize;
    private int currentResult;

    public int getPageStart() {
        if (pageStart <= 0)
            pageStart = 1;
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public int getCurrentResult() {
        currentResult = (getPageStart() - 1) * getPageSize();
        if (currentResult < 0)
            currentResult = 0;
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

}
