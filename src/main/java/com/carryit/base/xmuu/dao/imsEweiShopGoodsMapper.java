package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.imsEweiShopGoods;
import com.carryit.base.xmuu.entity.imsEweiShopGoodsWithBLOBs;

public interface imsEweiShopGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopGoodsWithBLOBs record);

    int insertSelective(imsEweiShopGoodsWithBLOBs record);

    imsEweiShopGoodsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopGoodsWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopGoods record);
}