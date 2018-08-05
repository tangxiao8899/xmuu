package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopMember WithBLOBs;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;

public interface imsEweiShopMember Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopMember WithBLOBs record);

    int insertSelective(imsEweiShopMember WithBLOBs record);

    imsEweiShopMember WithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopMember WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopMember WithBLOBs record);

    int updateByPrimaryKey(imsEweiShopMember record);
}