package com.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2017/8/3.
 */
public class Page<T> implements Serializable {

    private int pageSize;
    private long totalSize;
    private int currentSize;
    private List<T> list;


    public long getTotalPage() {
       totalPage=  (totalSize + pageSize - 1) / pageSize;
        return totalPage;
    }

    public long totalPage;


    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

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
