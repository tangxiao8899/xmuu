package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopCreditshopGoods WithBLOBs;
import com.carryit.base.besttmwuu.entity.imsEweiShopCreditshopGoods;

public interface imsEweiShopCreditshopGoods Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopCreditshopGoods WithBLOBs record);

    int insertSelective(imsEweiShopCreditshopGoods WithBLOBs record);

    imsEweiShopCreditshopGoods WithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopCreditshopGoods WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopCreditshopGoods WithBLOBs record);

    int updateByPrimaryKey(imsEweiShopCreditshopGoods record);
}