package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.UserDao;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public boolean addUser(User record) {
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

	@Override
	public User selectByPhone(String phone) {
		 return userDao.selectByPhone(phone);
	}

}