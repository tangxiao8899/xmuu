package com.carryit.base.besttmwuu.entity;

public class imsEweiShopMerchRegWithBLOBs extends imsEweiShopMerchReg {
    private String diyformdata;

    private String diyformfields;

    private String reason;

    public String getDiyformdata() {
        return diyformdata;
    }

    public void setDiyformdata(String diyformdata) {
        this.diyformdata = diyformdata == null ? null : diyformdata.trim();
    }

    public String getDiyformfields() {
        return diyformfields;
    }

    public void setDiyformfields(String diyformfields) {
        this.diyformfields = diyformfields == null ? null : diyformfields.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}