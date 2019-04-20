package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.UserLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLevelDao {
    List<UserLevel> getLevel();
}
