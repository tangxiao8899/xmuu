package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsMcMembers;
import com.carryit.base.besttmwuu.entity.imsMcMembersWithBLOBs;

public interface imsMcMembersMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(imsMcMembersWithBLOBs record);

    int insertSelective(imsMcMembersWithBLOBs record);

    imsMcMembersWithBLOBs selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(imsMcMembersWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsMcMembersWithBLOBs record);

    int updateByPrimaryKey(imsMcMembers record);
}