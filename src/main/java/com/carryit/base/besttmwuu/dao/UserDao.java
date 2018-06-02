package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.User;
import org.apache.ibatis.annotations.Mapper;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
