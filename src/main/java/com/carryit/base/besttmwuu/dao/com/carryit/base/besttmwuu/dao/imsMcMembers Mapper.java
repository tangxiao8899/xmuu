package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsMcMembers WithBLOBs;
import com.carryit.base.besttmwuu.entity.imsMcMembers;

public interface imsMcMembers Mapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(imsMcMembers WithBLOBs record);

    int insertSelective(imsMcMembers WithBLOBs record);

    imsMcMembers WithBLOBs selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(imsMcMembers WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsMcMembers WithBLOBs record);

    int updateByPrimaryKey(imsMcMembers record);
}