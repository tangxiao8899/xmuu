package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.ImsUserCapitalFlowEntity;

import java.util.List;

public interface ImsUserCapitalFlowService {
    /**
     * 保存账户流水
     * @param entity
     */
    void save(ImsUserCapitalFlowEntity entity);

    List<ImsUserCapitalFlowEntity> getBillsbyUid(Integer uid);
}
