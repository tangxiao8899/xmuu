package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopSnsLevel;

public interface imsEweiShopSnsLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopSnsLevel record);

    int insertSelective(imsEweiShopSnsLevel record);

    imsEweiShopSnsLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopSnsLevel record);

    int updateByPrimaryKey(imsEweiShopSnsLevel record);
}