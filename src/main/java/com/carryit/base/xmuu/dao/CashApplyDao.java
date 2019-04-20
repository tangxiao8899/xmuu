package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.CashApply;
import com.carryit.base.besttmwuu.entity.CashDataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 提现数据
     * @param phone 提现手机号
     * @return
     */
    List<CashDataDTO> cashData(@Param("phone") String phone,@Param("page") int page,@Param("limit") int limit);

    int count(@Param("phone") String phone);
}
