package com.bean.req;

import com.bean.CommonRequest;

public class WxPayReq extends CommonRequest {

    private String remoteAddrIP;
    private int productId;
    private int productNum;
    private int uid;
    private int bid;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getRemoteAddrIP() {
        return remoteAddrIP;
    }

    public void setRemoteAddrIP(String remoteAddrIP) {
        this.remoteAddrIP = remoteAddrIP;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
