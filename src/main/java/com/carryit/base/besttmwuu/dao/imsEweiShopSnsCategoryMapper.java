package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopSnsCategory;

public interface imsEweiShopSnsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopSnsCategory record);

    int insertSelective(imsEweiShopSnsCategory record);

    imsEweiShopSnsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopSnsCategory record);

    int updateByPrimaryKey(imsEweiShopSnsCategory record);
}