package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.CashApply;
import org.springframework.stereotype.Repository;

@Repository
public interface CashApplyDao {

    /**
     * 保存
     * @param cashApply
     */
    void save(CashApply cashApply);

    /**
     * 修改
     * @param cashApply
     */
    void update(CashApply cashApply);

    /**
     * 查询最新的ID
     * @return
     */
    int findMaxId();
}
