package com.carryit.base.xmuu.service;

import com.alibaba.fastjson.JSONObject;

public interface ProfitService {
    /**
     * 我的收益
     * @param json
     * @return
     */
    JSONObject myProfit(String json);

    /**
     * 我的账单
     * @param json
     * @return
     */
    JSONObject myBill(String json);
}
