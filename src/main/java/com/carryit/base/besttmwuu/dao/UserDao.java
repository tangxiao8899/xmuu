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

    User selectByPrimaryKey(@Param("uid") Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByPhone(String phone);

    int updatePassWord(@Param("phone") String phone, @Param("password")String password);

    int updateUser(User user);

    User getUserByPoneAndPassword(String phone);

    //void updateByPhone(User user);
}
