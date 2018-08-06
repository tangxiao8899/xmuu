package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopMemberLevel;

public interface imsEweiShopMemberLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopMemberLevel record);

    int insertSelective(imsEweiShopMemberLevel record);

    imsEweiShopMemberLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopMemberLevel record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopMemberLevel record);

    int updateByPrimaryKey(imsEweiShopMemberLevel record);
}