package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByPhone(String phone);

    int updatePassWord(@Param("phone") String phone, @Param("password")String password);

    //void updateByPhone(User user);
}
