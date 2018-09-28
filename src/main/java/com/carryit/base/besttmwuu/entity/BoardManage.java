package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class BoardManage implements Serializable {

    private int uid;
    private int pageStart;
    private int pageSize;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPageStart() {
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
}
