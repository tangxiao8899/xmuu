package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopSnsBoardFollow;

public interface imsEweiShopSnsBoardFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopSnsBoardFollow record);

    int insertSelective(imsEweiShopSnsBoardFollow record);

    imsEweiShopSnsBoardFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopSnsBoardFollow record);

    int updateByPrimaryKey(imsEweiShopSnsBoardFollow record);
}