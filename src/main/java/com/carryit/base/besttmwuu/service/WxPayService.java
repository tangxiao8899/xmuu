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


    /**
     * 更新账户信息
     * @param out_trade_no 商户订单号
     * @param total_fee 订单总金额
     */
    void updateRechargeInfo(String out_trade_no,String total_fee);

}
