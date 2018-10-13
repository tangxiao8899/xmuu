package com.carryit.base.besttmwuu.dao;


import com.carryit.base.besttmwuu.entity.UserLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLevelDao {
    List<UserLevel> getLevel();
}
