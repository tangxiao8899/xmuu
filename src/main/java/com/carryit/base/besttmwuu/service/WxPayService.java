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
    JSONObject wxReward(String json );


    /**
     * 更新充值、提现账户信息
     * @param out_trade_no 商户订单号
     * @param total_fee 订单总金额
     */
    void updateRechargeInfo(String out_trade_no,String total_fee);

    /**
     * 更新打赏账户信息
     * @param fuid 打赏用户ID
     * @param tuid 被打赏用户ID
     * @param total_fee 订单总金额
     */
    void updateRewardInfo(String fuid, String tuid, String total_fee);

    /**
     * 微信提现
     * @param json
     * @return
     */
    JSONObject getCash(String json)throws Exception;

     /**
      * 报名支付
     * @param json
     * @throws Exception
     */
    JSONObject wxEntered(String json) throws Exception;

    /**
     * 账单查询
     * @param json
     * @return
     */
    JSONObject getBillsbyUid(String json);

    JSONObject manualCash(String json);
}
