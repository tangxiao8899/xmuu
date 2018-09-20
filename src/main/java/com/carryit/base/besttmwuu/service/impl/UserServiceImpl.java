package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carryit.base.besttmwuu.dao.UserDao;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public int updatePassWord(String json) {
        int i =0;
        JSONObject jo = JSON.parseObject(json);
        String phone = jo.getString("phone");
        String password = jo.getString("password");
        if(StringUtils.isEmpty(phone)&&StringUtils.isEmpty(password)){
           return i;
        }
        i = userDao.updatePassWord(phone, password);
        return i;

    }

//    @Override
//    public boolean updateByPhone(User user) {
//        boolean result = false;
//        try {
//            userDao.updateByPhone(user);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }

    @Override
	public User selectByPhone(String phone) {
		 return userDao.selectByPhone(phone);
	}

}