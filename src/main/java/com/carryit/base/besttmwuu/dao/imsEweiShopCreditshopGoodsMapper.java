package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopCreditshopGoods;
import com.carryit.base.besttmwuu.entity.imsEweiShopCreditshopGoodsWithBLOBs;

public interface imsEweiShopCreditshopGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopCreditshopGoodsWithBLOBs record);

    int insertSelective(imsEweiShopCreditshopGoodsWithBLOBs record);

    imsEweiShopCreditshopGoodsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopCreditshopGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopCreditshopGoodsWithBLOBs record);
    //cbx
    int updateByPrimaryKey(imsEweiShopCreditshopGoods record);

}