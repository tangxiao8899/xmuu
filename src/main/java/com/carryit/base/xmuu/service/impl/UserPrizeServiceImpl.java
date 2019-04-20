package com.carryit.base.xmuu.service.impl;


import com.carryit.base.xmuu.dao.UserPrizeDao;
import com.carryit.base.xmuu.entity.UserPrize;
import com.carryit.base.xmuu.service.UserPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userPrizeService")
public class UserPrizeServiceImpl implements UserPrizeService {

    @Autowired
    private UserPrizeDao userPrizeDao;

    @Override
    public int addUserPrize(UserPrize userPrize) {
        return userPrizeDao.addUserPrize(userPrize);
    }

    @Override
    public List<UserPrize> getAllUserPrize() {
        return userPrizeDao.getAllUserPrize();
    }

    @Override
    public UserPrize getUserPrizeById(UserPrize userPrize) {
        return userPrizeDao.getUserPrizeById(userPrize);
    }

    @Override
    public int deleteUserPrizeById(UserPrize userPrize) {
        return userPrizeDao.deleteUserPrizeById(userPrize);
    }
}
