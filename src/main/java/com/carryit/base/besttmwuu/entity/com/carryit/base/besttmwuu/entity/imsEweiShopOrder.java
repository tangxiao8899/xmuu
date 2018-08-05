package com.carryit.base.besttmwuu.entity;

import java.math.BigDecimal;

public class imsEweiShopOrder {
    private Integer id;

    private Integer uniacid;

    private String openid;

    private Integer agentid;

    private String ordersn;

    private BigDecimal price;

    private BigDecimal goodsprice;

    private BigDecimal discountprice;

    private Byte status;

    private Boolean paytype;

    private String transid;

    private String remark;

    private Integer addressid;

    private BigDecimal dispatchprice;

    private Integer dispatchid;

    private Integer createtime;

    private Byte dispatchtype;

    private Integer refundid;

    private Byte iscomment;

    private Byte creditadd;

    private Byte deleted;

    private Byte userdeleted;

    private Integer finishtime;

    private Integer paytime;

    private String expresscom;

    private String expresssn;

    private String express;

    private Integer sendtime;

    private Integer fetchtime;

    private Byte cash;

    private Integer canceltime;

    private Integer cancelpaytime;

    private Integer refundtime;

    private Byte isverify;

    private Byte verified;

    private String verifyopenid;

    private String verifycode;

    private Integer verifytime;

    private Integer verifystoreid;

    private BigDecimal deductprice;

    private Integer deductcredit;

    private BigDecimal deductcredit2;

    private BigDecimal deductenough;

    private Integer virtual;

    private Byte sysdeleted;

    private Integer ordersn2;

    private BigDecimal changeprice;

    private BigDecimal changedispatchprice;

    private BigDecimal oldprice;

    private BigDecimal olddispatchprice;

    private Byte isvirtual;

    private Integer couponid;

    private BigDecimal couponprice;

    private Integer diyformid;

    private Integer storeid;

    private Boolean printstate;

    private Boolean printstate2;

    private Byte refundstate;

    private Integer ismr;

    private BigDecimal isdiscountprice;

    private Boolean isvirtualsend;

    private Boolean verifytype;

    private String invoicename;

    private Integer merchid;

    private Boolean ismerch;

    private Integer parentid;

    private Boolean isparent;

    private BigDecimal grprice;

    private Boolean merchshow;

    private BigDecimal merchdeductenough;

    private Integer couponmerchid;

    private Byte isglobonus;

    private Boolean merchapply;

    private Byte isabonus;

    private Byte isborrow;

    private String borrowopenid;

    private BigDecimal merchisdiscountprice;

    private Byte apppay;

    private BigDecimal coupongoodprice;

    private BigDecimal buyagainprice;

    private Integer authorid;

    private Boolean isauthor;

    private Byte ispackage;

    private Integer packageid;

    private BigDecimal taskdiscountprice;

    private BigDecimal seckilldiscountprice;

    private Integer verifyendtime;

    private Boolean willcancelmessage;

    private Byte sendtype;

    private BigDecimal lotterydiscountprice;

    private Boolean contype;

    private Integer wxid;

    private String wxcardid;

    private String wxcode;

    private String dispatchkey;

    private Integer quickid;

    private Byte istrade;

    private Byte isnewstore;

    private Integer liveid;

    private String ordersnTrade;

    private Boolean tradestatus;

    private Boolean tradepaytype;

    private Integer tradepaytime;

    private BigDecimal dowpayment;

    private BigDecimal betweenprice;

    private Integer isshare;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUniacid() {
        return uniacid;
    }

    public void setUniacid(Integer uniacid) {
        this.uniacid = uniacid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn == null ? null : ordersn.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(BigDecimal goodsprice) {
        this.goodsprice = goodsprice;
    }

    public BigDecimal getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(BigDecimal discountprice) {
        this.discountprice = discountprice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getPaytype() {
        return paytype;
    }

    public void setPaytype(Boolean paytype) {
        this.paytype = paytype;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid == null ? null : transid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public BigDecimal getDispatchprice() {
        return dispatchprice;
    }

    public void setDispatchprice(BigDecimal dispatchprice) {
        this.dispatchprice = dispatchprice;
    }

    public Integer getDispatchid() {
        return dispatchid;
    }

    public void setDispatchid(Integer dispatchid) {
        this.dispatchid = dispatchid;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Byte getDispatchtype() {
        return dispatchtype;
    }

    public void setDispatchtype(Byte dispatchtype) {
        this.dispatchtype = dispatchtype;
    }

    public Integer getRefundid() {
        return refundid;
    }

    public void setRefundid(Integer refundid) {
        this.refundid = refundid;
    }

    public Byte getIscomment() {
        return iscomment;
    }

    public void setIscomment(Byte iscomment) {
        this.iscomment = iscomment;
    }

    public Byte getCreditadd() {
        return creditadd;
    }

    public void setCreditadd(Byte creditadd) {
        this.creditadd = creditadd;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Byte getUserdeleted() {
        return userdeleted;
    }

    public void setUserdeleted(Byte userdeleted) {
        this.userdeleted = userdeleted;
    }

    public Integer getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Integer finishtime) {
        this.finishtime = finishtime;
    }

    public Integer getPaytime() {
        return paytime;
    }

    public void setPaytime(Integer paytime) {
        this.paytime = paytime;
    }

    public String getExpresscom() {
        return expresscom;
    }

    public void setExpresscom(String expresscom) {
        this.expresscom = expresscom == null ? null : expresscom.trim();
    }

    public String getExpresssn() {
        return expresssn;
    }

    public void setExpresssn(String expresssn) {
        this.expresssn = expresssn == null ? null : expresssn.trim();
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public Integer getSendtime() {
        return sendtime;
    }

    public void setSendtime(Integer sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getFetchtime() {
        return fetchtime;
    }

    public void setFetchtime(Integer fetchtime) {
        this.fetchtime = fetchtime;
    }

    public Byte getCash() {
        return cash;
    }

    public void setCash(Byte cash) {
        this.cash = cash;
    }

    public Integer getCanceltime() {
        return canceltime;
    }

    public void setCanceltime(Integer canceltime) {
        this.canceltime = canceltime;
    }

    public Integer getCancelpaytime() {
        return cancelpaytime;
    }

    public void setCancelpaytime(Integer cancelpaytime) {
        this.cancelpaytime = cancelpaytime;
    }

    public Integer getRefundtime() {
        return refundtime;
    }

    public void setRefundtime(Integer refundtime) {
        this.refundtime = refundtime;
    }

    public Byte getIsverify() {
        return isverify;
    }

    public void setIsverify(Byte isverify) {
        this.isverify = isverify;
    }

    public Byte getVerified() {
        return verified;
    }

    public void setVerified(Byte verified) {
        this.verified = verified;
    }

    public String getVerifyopenid() {
        return verifyopenid;
    }

    public void setVerifyopenid(String verifyopenid) {
        this.verifyopenid = verifyopenid == null ? null : verifyopenid.trim();
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode == null ? null : verifycode.trim();
    }

    public Integer getVerifytime() {
        return verifytime;
    }

    public void setVerifytime(Integer verifytime) {
        this.verifytime = verifytime;
    }

    public Integer getVerifystoreid() {
        return verifystoreid;
    }

    public void setVerifystoreid(Integer verifystoreid) {
        this.verifystoreid = verifystoreid;
    }

    public BigDecimal getDeductprice() {
        return deductprice;
    }

    public void setDeductprice(BigDecimal deductprice) {
        this.deductprice = deductprice;
    }

    public Integer getDeductcredit() {
        return deductcredit;
    }

    public void setDeductcredit(Integer deductcredit) {
        this.deductcredit = deductcredit;
    }

    public BigDecimal getDeductcredit2() {
        return deductcredit2;
    }

    public void setDeductcredit2(BigDecimal deductcredit2) {
        this.deductcredit2 = deductcredit2;
    }

    public BigDecimal getDeductenough() {
        return deductenough;
    }

    public void setDeductenough(BigDecimal deductenough) {
        this.deductenough = deductenough;
    }

    public Integer getVirtual() {
        return virtual;
    }

    public void setVirtual(Integer virtual) {
        this.virtual = virtual;
    }

    public Byte getSysdeleted() {
        return sysdeleted;
    }

    public void setSysdeleted(Byte sysdeleted) {
        this.sysdeleted = sysdeleted;
    }

    public Integer getOrdersn2() {
        return ordersn2;
    }

    public void setOrdersn2(Integer ordersn2) {
        this.ordersn2 = ordersn2;
    }

    public BigDecimal getChangeprice() {
        return changeprice;
    }

    public void setChangeprice(BigDecimal changeprice) {
        this.changeprice = changeprice;
    }

    public BigDecimal getChangedispatchprice() {
        return changedispatchprice;
    }

    public void setChangedispatchprice(BigDecimal changedispatchprice) {
        this.changedispatchprice = changedispatchprice;
    }

    public BigDecimal getOldprice() {
        return oldprice;
    }

    public void setOldprice(BigDecimal oldprice) {
        this.oldprice = oldprice;
    }

    public BigDecimal getOlddispatchprice() {
        return olddispatchprice;
    }

    public void setOlddispatchprice(BigDecimal olddispatchprice) {
        this.olddispatchprice = olddispatchprice;
    }

    public Byte getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Byte isvirtual) {
        this.isvirtual = isvirtual;
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public BigDecimal getCouponprice() {
        return couponprice;
    }

    public void setCouponprice(BigDecimal couponprice) {
        this.couponprice = couponprice;
    }

    public Integer getDiyformid() {
        return diyformid;
    }

    public void setDiyformid(Integer diyformid) {
        this.diyformid = diyformid;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Boolean getPrintstate() {
        return printstate;
    }

    public void setPrintstate(Boolean printstate) {
        this.printstate = printstate;
    }

    public Boolean getPrintstate2() {
        return printstate2;
    }

    public void setPrintstate2(Boolean printstate2) {
        this.printstate2 = printstate2;
    }

    public Byte getRefundstate() {
        return refundstate;
    }

    public void setRefundstate(Byte refundstate) {
        this.refundstate = refundstate;
    }

    public Integer getIsmr() {
        return ismr;
    }

    public void setIsmr(Integer ismr) {
        this.ismr = ismr;
    }

    public BigDecimal getIsdiscountprice() {
        return isdiscountprice;
    }

    public void setIsdiscountprice(BigDecimal isdiscountprice) {
        this.isdiscountprice = isdiscountprice;
    }

    public Boolean getIsvirtualsend() {
        return isvirtualsend;
    }

    public void setIsvirtualsend(Boolean isvirtualsend) {
        this.isvirtualsend = isvirtualsend;
    }

    public Boolean getVerifytype() {
        return verifytype;
    }

    public void setVerifytype(Boolean verifytype) {
        this.verifytype = verifytype;
    }

    public String getInvoicename() {
        return invoicename;
    }

    public void setInvoicename(String invoicename) {
        this.invoicename = invoicename == null ? null : invoicename.trim();
    }

    public Integer getMerchid() {
        return merchid;
    }

    public void setMerchid(Integer merchid) {
        this.merchid = merchid;
    }

    public Boolean getIsmerch() {
        return ismerch;
    }

    public void setIsmerch(Boolean ismerch) {
        this.ismerch = ismerch;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Boolean getIsparent() {
        return isparent;
    }

    public void setIsparent(Boolean isparent) {
        this.isparent = isparent;
    }

    public BigDecimal getGrprice() {
        return grprice;
    }

    public void setGrprice(BigDecimal grprice) {
        this.grprice = grprice;
    }

    public Boolean getMerchshow() {
        return merchshow;
    }

    public void setMerchshow(Boolean merchshow) {
        this.merchshow = merchshow;
    }

    public BigDecimal getMerchdeductenough() {
        return merchdeductenough;
    }

    public void setMerchdeductenough(BigDecimal merchdeductenough) {
        this.merchdeductenough = merchdeductenough;
    }

    public Integer getCouponmerchid() {
        return couponmerchid;
    }

    public void setCouponmerchid(Integer couponmerchid) {
        this.couponmerchid = couponmerchid;
    }

    public Byte getIsglobonus() {
        return isglobonus;
    }

    public void setIsglobonus(Byte isglobonus) {
        this.isglobonus = isglobonus;
    }

    public Boolean getMerchapply() {
        return merchapply;
    }

    public void setMerchapply(Boolean merchapply) {
        this.merchapply = merchapply;
    }

    public Byte getIsabonus() {
        return isabonus;
    }

    public void setIsabonus(Byte isabonus) {
        this.isabonus = isabonus;
    }

    public Byte getIsborrow() {
        return isborrow;
    }

    public void setIsborrow(Byte isborrow) {
        this.isborrow = isborrow;
    }

    public String getBorrowopenid() {
        return borrowopenid;
    }

    public void setBorrowopenid(String borrowopenid) {
        this.borrowopenid = borrowopenid == null ? null : borrowopenid.trim();
    }

    public BigDecimal getMerchisdiscountprice() {
        return merchisdiscountprice;
    }

    public void setMerchisdiscountprice(BigDecimal merchisdiscountprice) {
        this.merchisdiscountprice = merchisdiscountprice;
    }

    public Byte getApppay() {
        return apppay;
    }

    public void setApppay(Byte apppay) {
        this.apppay = apppay;
    }

    public BigDecimal getCoupongoodprice() {
        return coupongoodprice;
    }

    public void setCoupongoodprice(BigDecimal coupongoodprice) {
        this.coupongoodprice = coupongoodprice;
    }

    public BigDecimal getBuyagainprice() {
        return buyagainprice;
    }

    public void setBuyagainprice(BigDecimal buyagainprice) {
        this.buyagainprice = buyagainprice;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Boolean getIsauthor() {
        return isauthor;
    }

    public void setIsauthor(Boolean isauthor) {
        this.isauthor = isauthor;
    }

    public Byte getIspackage() {
        return ispackage;
    }

    public void setIspackage(Byte ispackage) {
        this.ispackage = ispackage;
    }

    public Integer getPackageid() {
        return packageid;
    }

    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    public BigDecimal getTaskdiscountprice() {
        return taskdiscountprice;
    }

    public void setTaskdiscountprice(BigDecimal taskdiscountprice) {
        this.taskdiscountprice = taskdiscountprice;
    }

    public BigDecimal getSeckilldiscountprice() {
        return seckilldiscountprice;
    }

    public void setSeckilldiscountprice(BigDecimal seckilldiscountprice) {
        this.seckilldiscountprice = seckilldiscountprice;
    }

    public Integer getVerifyendtime() {
        return verifyendtime;
    }

    public void setVerifyendtime(Integer verifyendtime) {
        this.verifyendtime = verifyendtime;
    }

    public Boolean getWillcancelmessage() {
        return willcancelmessage;
    }

    public void setWillcancelmessage(Boolean willcancelmessage) {
        this.willcancelmessage = willcancelmessage;
    }

    public Byte getSendtype() {
        return sendtype;
    }

    public void setSendtype(Byte sendtype) {
        this.sendtype = sendtype;
    }

    public BigDecimal getLotterydiscountprice() {
        return lotterydiscountprice;
    }

    public void setLotterydiscountprice(BigDecimal lotterydiscountprice) {
        this.lotterydiscountprice = lotterydiscountprice;
    }

    public Boolean getContype() {
        return contype;
    }

    public void setContype(Boolean contype) {
        this.contype = contype;
    }

    public Integer getWxid() {
        return wxid;
    }

    public void setWxid(Integer wxid) {
        this.wxid = wxid;
    }

    public String getWxcardid() {
        return wxcardid;
    }

    public void setWxcardid(String wxcardid) {
        this.wxcardid = wxcardid == null ? null : wxcardid.trim();
    }

    public String getWxcode() {
        return wxcode;
    }

    public void setWxcode(String wxcode) {
        this.wxcode = wxcode == null ? null : wxcode.trim();
    }

    public String getDispatchkey() {
        return dispatchkey;
    }

    public void setDispatchkey(String dispatchkey) {
        this.dispatchkey = dispatchkey == null ? null : dispatchkey.trim();
    }

    public Integer getQuickid() {
        return quickid;
    }

    public void setQuickid(Integer quickid) {
        this.quickid = quickid;
    }

    public Byte getIstrade() {
        return istrade;
    }

    public void setIstrade(Byte istrade) {
        this.istrade = istrade;
    }

    public Byte getIsnewstore() {
        return isnewstore;
    }

    public void setIsnewstore(Byte isnewstore) {
        this.isnewstore = isnewstore;
    }

    public Integer getLiveid() {
        return liveid;
    }

    public void setLiveid(Integer liveid) {
        this.liveid = liveid;
    }

    public String getOrdersnTrade() {
        return ordersnTrade;
    }

    public void setOrdersnTrade(String ordersnTrade) {
        this.ordersnTrade = ordersnTrade == null ? null : ordersnTrade.trim();
    }

    public Boolean getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(Boolean tradestatus) {
        this.tradestatus = tradestatus;
    }

    public Boolean getTradepaytype() {
        return tradepaytype;
    }

    public void setTradepaytype(Boolean tradepaytype) {
        this.tradepaytype = tradepaytype;
    }

    public Integer getTradepaytime() {
        return tradepaytime;
    }

    public void setTradepaytime(Integer tradepaytime) {
        this.tradepaytime = tradepaytime;
    }

    public BigDecimal getDowpayment() {
        return dowpayment;
    }

    public void setDowpayment(BigDecimal dowpayment) {
        this.dowpayment = dowpayment;
    }

    public BigDecimal getBetweenprice() {
        return betweenprice;
    }

    public void setBetweenprice(BigDecimal betweenprice) {
        this.betweenprice = betweenprice;
    }

    public Integer getIsshare() {
        return isshare;
    }

    public void setIsshare(Integer isshare) {
        this.isshare = isshare;
    }
}