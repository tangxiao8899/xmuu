package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CashApply implements Serializable {
    private int id; //主键ID
    private String openid; //OPENID
    private int type; //类型 0 充值 1 提现
    private String logno; //openid 系统单号 SH 代表购物单号（企业支付退款单号一致） RC 代表充值单号 ( 企业支付退款单号一致） RW 代表余额提现单号 CA 代表佣金提现单号
    private String title; //	名字
    private int createtime; //建立时间
    private int status; //	状态 type 0 (0 生成 1 成功 -1 失败 3 退款) type 1 (0 申请提现 1 完成 -1 失败)
    private BigDecimal money; //金额 private String rechargetype; //充值类型 wechat 微信支付,;alipay 支付宝; system 后台充值; system1 后台退款 ;alipaym 支付宝直接到个人帐号汇款

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLogno() {
        return logno;
    }

    public void setLogno(String logno) {
        this.logno = logno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

}
