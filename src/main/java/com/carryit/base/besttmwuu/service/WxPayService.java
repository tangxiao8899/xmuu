package com.carryit.base.besttmwuu.service;

import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;

/**
 * 微信支付服务
 */
public interface WxPayService {
    /**
     * 微信支付
     * @param json
     * @return
     */
    JSONObject wxPay(String json ) throws Exception;


    /**
     * 微信充值
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject wxRecharge(String json ) throws Exception;


    /**
     * 微信打赏
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject wxReward(String json ) throws Exception;

}
