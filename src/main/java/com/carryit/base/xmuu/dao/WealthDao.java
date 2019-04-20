package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WealthDao {

    List<UserDTO> onTheList(@Param("startTime") long startTime, @Param("endTime") long endTime);

    List<UserDTO> queryPage(@Param("startTime") long startTime, @Param("endTime") long endTime);

    long queryPageCount(@Param("startTime") long startTime, @Param("endTime") long endTime);
}
