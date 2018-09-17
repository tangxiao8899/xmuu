package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WealthDao {

    List<UserDTO> onTheList(@Param("startTime") long startTime, @Param("endTime") long endTime);

    List<UserDTO> queryPage(@Param("startTime") long startTime, @Param("endTime") long endTime);

    long queryPageCount(@Param("startTime") long startTime, @Param("endTime") long endTime);
}
