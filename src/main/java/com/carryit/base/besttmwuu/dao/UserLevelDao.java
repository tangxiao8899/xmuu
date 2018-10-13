package com.carryit.base.besttmwuu.dao;


import com.carryit.base.besttmwuu.entity.UserLevel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLevelDao {
    UserLevel getLevel();
}
