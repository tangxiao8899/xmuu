package com.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2017/8/3.
 */
public class Page<T> implements Serializable {

    private int pageSize;
    private long totalSize;
    private List<T> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }



}
