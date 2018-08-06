package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopOrder;
import com.carryit.base.besttmwuu.entity.imsEweiShopOrderWithBLOBs;

public interface imsEweiShopOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopOrderWithBLOBs record);

    int insertSelective(imsEweiShopOrderWithBLOBs record);

    imsEweiShopOrderWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopOrderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopOrderWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopOrder record);
}