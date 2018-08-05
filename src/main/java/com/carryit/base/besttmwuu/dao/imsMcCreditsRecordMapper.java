package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsMcCreditsRecord;

public interface imsMcCreditsRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsMcCreditsRecord record);

    int insertSelective(imsMcCreditsRecord record);

    imsMcCreditsRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsMcCreditsRecord record);

    int updateByPrimaryKey(imsMcCreditsRecord record);
}