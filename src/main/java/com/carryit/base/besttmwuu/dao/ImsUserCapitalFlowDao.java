package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.ImsUserCapitalFlowEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ImsUserCapitalFlowDao {

    /**
     * 保存账户流水
     * @param entity
     */
    void save(ImsUserCapitalFlowEntity entity);
}
