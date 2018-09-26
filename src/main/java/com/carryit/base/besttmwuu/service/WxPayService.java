package com.carryit.base.besttmwuu.service;

import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;

/**
 * 微信支付服务
 */
public interface WxPayService {
    /**
     * 获取微信预订单
     * @param json
     * @return
     */
    JSONObject wxPay(String json ) throws Exception;
    ;
}
