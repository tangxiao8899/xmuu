package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopZrbglobonus;

public interface imsEweiShopZrbglobonusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopZrbglobonus record);

    int insertSelective(imsEweiShopZrbglobonus record);

    imsEweiShopZrbglobonus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopZrbglobonus record);

    int updateByPrimaryKey(imsEweiShopZrbglobonus record);
}