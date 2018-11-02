package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.CashApply;

public interface CashApplyService {

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
     * 插叙最新的一个ID
     * @return
     */
    int findMaxId();
}
