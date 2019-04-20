package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.imsEweiShopSnsBoardFollow;

public interface imsEweiShopSnsBoardFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopSnsBoardFollow record);

    int insertSelective(imsEweiShopSnsBoardFollow record);

    imsEweiShopSnsBoardFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopSnsBoardFollow record);

    int updateByPrimaryKey(imsEweiShopSnsBoardFollow record);
}