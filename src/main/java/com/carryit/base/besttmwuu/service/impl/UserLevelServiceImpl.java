package com.carryit.base.besttmwuu.service.impl;


import com.carryit.base.besttmwuu.dao.UserLevelDao;
import com.carryit.base.besttmwuu.entity.UserLevel;
import com.carryit.base.besttmwuu.service.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userLevelService")
public class UserLevelServiceImpl implements UserLevelService {
    @Autowired
    private UserLevelDao userLevelDao;


    @Override
    public List<UserLevel> getLevel() {
        return userLevelDao.getLevel();
    }
}
