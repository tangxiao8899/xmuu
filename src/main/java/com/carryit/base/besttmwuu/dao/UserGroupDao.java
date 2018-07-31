package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.UserGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupDao {

    List<UserGroup> getConcerned(@Param("userId") int userId);

}
