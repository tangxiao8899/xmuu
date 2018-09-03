package com.carryit.base.besttmwuu.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信支付服务
 */
public interface WxPayService {
    /**
     * 获取微信预订单
     * @param remoteAddrIP 终端ip
     * @param totalPrice 订单总金额
     * @return
     */
    JSONObject wxPay(String remoteAddrIP,String totalPrice) throws Exception;
    ;
}
