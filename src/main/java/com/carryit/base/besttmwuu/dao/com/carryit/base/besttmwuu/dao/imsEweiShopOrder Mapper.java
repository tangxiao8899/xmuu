package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopOrder WithBLOBs;
import com.carryit.base.besttmwuu.entity.imsEweiShopOrder;

public interface imsEweiShopOrder Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopOrder WithBLOBs record);

    int insertSelective(imsEweiShopOrder WithBLOBs record);

    imsEweiShopOrder WithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopOrder WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopOrder WithBLOBs record);

    int updateByPrimaryKey(imsEweiShopOrder record);
}