package com.carryit.base.xmuu.service;

import com.alibaba.fastjson.JSONObject;
import com.carryit.base.xmuu.entity.ImsUserCapitalFlowEntity;

import java.util.List;

public interface ImsUserCapitalFlowService {
    /**
     * 保存账户流水
     * @param entity
     */
    void save(ImsUserCapitalFlowEntity entity);

    List<ImsUserCapitalFlowEntity> getBillsbyUid(Integer uid);

    /**
     * 我的收益
     * @param uid
     * @return
     */
    Double getMyProfit(Integer uid);

    JSONObject getMyBill(Integer uid,Integer page,Integer pageSize);
}
