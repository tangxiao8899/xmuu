package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopGoods WithBLOBs;
import com.carryit.base.besttmwuu.entity.imsEweiShopGoods;

public interface imsEweiShopGoods Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopGoods WithBLOBs record);

    int insertSelective(imsEweiShopGoods WithBLOBs record);

    imsEweiShopGoods WithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopGoods WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopGoods WithBLOBs record);

    int updateByPrimaryKey(imsEweiShopGoods record);
}