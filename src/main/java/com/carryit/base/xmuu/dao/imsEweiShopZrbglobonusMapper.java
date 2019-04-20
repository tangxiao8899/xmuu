package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.imsEweiShopZrbglobonus;

public interface imsEweiShopZrbglobonusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopZrbglobonus record);

    int insertSelective(imsEweiShopZrbglobonus record);

    imsEweiShopZrbglobonus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopZrbglobonus record);

    int updateByPrimaryKey(imsEweiShopZrbglobonus record);
}