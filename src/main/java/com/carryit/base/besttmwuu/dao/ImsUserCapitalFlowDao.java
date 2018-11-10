package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.ImsUserCapitalFlowEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImsUserCapitalFlowDao {

    /**
     * 保存账户流水
     * @param entity
     */
    void save(ImsUserCapitalFlowEntity entity);

    List<ImsUserCapitalFlowEntity> getBillsbyUid(Integer uid);
}
