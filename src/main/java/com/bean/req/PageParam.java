package com.bean.req;

import com.bean.CommonRequest;

public class PageParam extends CommonRequest {

    private int pageStart;
    private int pageSize;

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
