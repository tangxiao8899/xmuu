package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.imsMcMembers;
import com.carryit.base.xmuu.entity.imsMcMembersWithBLOBs;

public interface imsMcMembersMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(imsMcMembersWithBLOBs record);

    int insertSelective(imsMcMembersWithBLOBs record);

    imsMcMembersWithBLOBs selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(imsMcMembersWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsMcMembersWithBLOBs record);

    int updateByPrimaryKey(imsMcMembers record);
}