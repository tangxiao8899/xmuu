package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopSnsBoard WithBLOBs;
import com.carryit.base.besttmwuu.entity.imsEweiShopSnsBoard;

public interface imsEweiShopSnsBoard Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopSnsBoard WithBLOBs record);

    int insertSelective(imsEweiShopSnsBoard WithBLOBs record);

    imsEweiShopSnsBoard WithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopSnsBoard WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopSnsBoard WithBLOBs record);

    int updateByPrimaryKey(imsEweiShopSnsBoard record);
}