package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopGoods;
import com.carryit.base.besttmwuu.entity.imsEweiShopGoodsWithBLOBs;

public interface imsEweiShopGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopGoodsWithBLOBs record);

    int insertSelective(imsEweiShopGoodsWithBLOBs record);

    imsEweiShopGoodsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopGoodsWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopGoods record);
}