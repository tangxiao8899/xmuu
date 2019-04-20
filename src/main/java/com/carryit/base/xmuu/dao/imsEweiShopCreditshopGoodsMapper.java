package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.imsEweiShopCreditshopGoods;
import com.carryit.base.xmuu.entity.imsEweiShopCreditshopGoodsWithBLOBs;

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