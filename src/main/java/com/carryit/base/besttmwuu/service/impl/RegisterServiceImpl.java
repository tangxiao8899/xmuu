package com.carryit.base.besttmwuu.service.impl;

import com.bean.RegisterReq;
import com.carryit.base.besttmwuu.dao.UserDao;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("registerService")
public class RegisterServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public User beiginRegister(RegisterReq req) {
    		User result = userDao.selectByPhone(req.phone);
    		if(result!=null) {
    			//已经注册了
    		}
    		return new User();
    }
    public User selectByPhone(String phone) {
        return userDao.selectByPhone(phone);
    }
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

}