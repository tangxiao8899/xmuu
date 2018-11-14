package com.carryit.base.besttmwuu.service;


import com.carryit.base.besttmwuu.entity.CashApply;
import com.carryit.base.besttmwuu.entity.CashDataDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


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

    /**
     * 提现数据
     * @param phone 提现手机号
     * @return
     */
    List<CashDataDTO> cashData(@Param("phone") String phone,@Param("page") int page,@Param("limit") int limit);

    /**
     * 提现统计数量
     * @param phone
     * @return
     */
    int count(@Param("phone") String phone);

    /**
     * 修改提现状态
     * @param dto
     */
    void updateCash(CashDataDTO dto);
}
