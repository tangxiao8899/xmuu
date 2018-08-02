package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.UserGroupDao;
import com.carryit.base.besttmwuu.entity.UserGroup;
import com.carryit.base.besttmwuu.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupDao userGroupDao;
    @Override
    public List<UserGroup> getConcerned(int userId) {
        return userGroupDao.getConcerned(userId);
    }
}
