package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopMember;
import com.carryit.base.besttmwuu.entity.imsEweiShopMemberWithBLOBs;

public interface imsEweiShopMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopMemberWithBLOBs record);

    int insertSelective(imsEweiShopMemberWithBLOBs record);

    imsEweiShopMemberWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopMemberWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopMemberWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopMember record);
}