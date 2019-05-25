package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.ImsUserCapitalFlowEntity;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 我的总收益
     * @param uid
     * @return
     */
    Double getMyProfit(Integer uid);

    /**
     * 我的账单
     * @param uid
     * @param index
     * @param pageSize
     * @return
     */
    List<ImsUserCapitalFlowEntity> getMyBill(@Param("uid") Integer uid, @Param("page") int index, @Param("pageSize") Integer pageSize);
}
