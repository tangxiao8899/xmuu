package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.ImsUserCapitalFlowEntity;

import java.util.List;

public interface ImsUserCapitalFlowService {
    /**
     * 保存账户流水
     * @param entity
     */
    void save(ImsUserCapitalFlowEntity entity);

    List<ImsUserCapitalFlowEntity> getBillsbyUid(Integer uid);
}
