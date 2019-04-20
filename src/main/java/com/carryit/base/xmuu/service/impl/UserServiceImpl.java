package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.dao.UserDao;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.HximService;
import com.carryit.base.besttmwuu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Autowired
    private HximService hximService;


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
    @Transactional
    public int updatePassWord(String json) {
        int i =0;
        JSONObject jo = JSON.parseObject(json);
        String phone = jo.getString("phone");
        String password = jo.getString("password");
        if(StringUtils.isEmpty(phone)&&StringUtils.isEmpty(password)){
           return i;
        }
        i = userDao.updatePassWord(phone, password);

        //修改环信密码
        //1、获取环信token
        String token = null;
        ResultPojo rp = null;
        try {
            rp = hximService.getToken();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        JSONObject subjo = JSON.parseObject(rp.getData().toString());
        if(subjo.containsKey("access_token")){
            token = subjo.getString("access_token");
        }
        //2、同步环信密码
        if(!StringUtils.isEmpty(token)){
            JSONObject sunJo = new JSONObject();
            sunJo.put("token",token);
            sunJo.put("username",phone);
            sunJo.put("newpassword",password);
            try {
                hximService.resetPassword(sunJo.toJSONString());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
            i = 1;
        }

        return i;

    }

    @Override
    public User getUserByPoneAndPassword(String phone) {
        return userDao.getUserByPoneAndPassword(phone);
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