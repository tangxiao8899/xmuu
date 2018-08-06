package com.carryit.base.besttmwuu.entity;

public class imsEweiShopOrderWithBLOBs extends imsEweiShopOrder {
    private String carrier;

    private String virtualInfo;

    private String virtualStr;

    private String address;

    private String diyformdata;

    private String diyformfields;

    private String addressSend;

    private String closereason;

    private String remarksaler;

    private String remarkclose;

    private String remarksend;

    private String virtualsendInfo;

    private String verifyinfo;

    private String verifycodes;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier == null ? null : carrier.trim();
    }

    public String getVirtualInfo() {
        return virtualInfo;
    }

    public void setVirtualInfo(String virtualInfo) {
        this.virtualInfo = virtualInfo == null ? null : virtualInfo.trim();
    }

    public String getVirtualStr() {
        return virtualStr;
    }

    public void setVirtualStr(String virtualStr) {
        this.virtualStr = virtualStr == null ? null : virtualStr.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

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

    public String getAddressSend() {
        return addressSend;
    }

    public void setAddressSend(String addressSend) {
        this.addressSend = addressSend == null ? null : addressSend.trim();
    }

    public String getClosereason() {
        return closereason;
    }

    public void setClosereason(String closereason) {
        this.closereason = closereason == null ? null : closereason.trim();
    }

    public String getRemarksaler() {
        return remarksaler;
    }

    public void setRemarksaler(String remarksaler) {
        this.remarksaler = remarksaler == null ? null : remarksaler.trim();
    }

    public String getRemarkclose() {
        return remarkclose;
    }

    public void setRemarkclose(String remarkclose) {
        this.remarkclose = remarkclose == null ? null : remarkclose.trim();
    }

    public String getRemarksend() {
        return remarksend;
    }

    public void setRemarksend(String remarksend) {
        this.remarksend = remarksend == null ? null : remarksend.trim();
    }

    public String getVirtualsendInfo() {
        return virtualsendInfo;
    }

    public void setVirtualsendInfo(String virtualsendInfo) {
        this.virtualsendInfo = virtualsendInfo == null ? null : virtualsendInfo.trim();
    }

    public String getVerifyinfo() {
        return verifyinfo;
    }

    public void setVerifyinfo(String verifyinfo) {
        this.verifyinfo = verifyinfo == null ? null : verifyinfo.trim();
    }

    public String getVerifycodes() {
        return verifycodes;
    }

    public void setVerifycodes(String verifycodes) {
        this.verifycodes = verifycodes == null ? null : verifycodes.trim();
    }
}