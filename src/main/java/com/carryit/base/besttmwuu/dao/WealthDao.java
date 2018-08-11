package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WealthDao {

    List<UserDTO> onTheList(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
