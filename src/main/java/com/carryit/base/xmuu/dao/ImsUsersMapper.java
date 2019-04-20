package com.carryit.base.xmuu.dao;

import java.util.List;

import com.carryit.base.xmuu.entity.ImsUsers;
import com.carryit.base.xmuu.entity.ImsUsersExample;
import org.apache.ibatis.annotations.Param;

public interface ImsUsersMapper {
    int countByExample(ImsUsersExample example);

    int deleteByExample(ImsUsersExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(ImsUsers record);

    int insertSelective(ImsUsers record);

    List<ImsUsers> selectByExample(ImsUsersExample example);

    ImsUsers selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") ImsUsers record, @Param("example") ImsUsersExample example);

    int updateByExample(@Param("record") ImsUsers record, @Param("example") ImsUsersExample example);

    int updateByPrimaryKeySelective(ImsUsers record);

    int updateByPrimaryKey(ImsUsers record);
}