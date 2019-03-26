package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.bean.RegisterReq;
import com.carryit.base.besttmwuu.dao.UserDao;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.HximService;
import com.carryit.base.besttmwuu.service.MemberService;
import com.carryit.base.besttmwuu.service.UserService;
import com.util.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service("registerService")
public class RegisterServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Autowired
    private HximService hximService;

    @Autowired
    private MemberService memberService;


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

    @Transactional
    public boolean addUser(User record) {
        boolean result = false;
        try {
            //注册到平台
            int uid = userDao.insertSelective(record);

            //新增一条会员资料记录
            Member member = new Member();
            member.setNickName(NameUtil.getName());
            member.setZhuquanzi(record.getBid());
            member.setUid(record.getUid());
            member.setCreatetime(new Date().getTime()+"");
            member.setAvatar("http://47.105.163.51/besttmwuu-0.0.1/index_img/logo.jpg");
            member.setLevel("5");
            memberService.addMember(member);
            //同步注册到环信
            //1、获取环信token
            String token = null;
            ResultPojo rp = hximService.getToken();
            JSONObject jo = JSON.parseObject(rp.getData().toString());
            if(jo.containsKey("access_token")){
                token = jo.getString("access_token");
            }
            //2、注册到环信
            if(!StringUtils.isEmpty(token)){
                JSONObject sunJo = new JSONObject();
                sunJo.put("token",token);
                sunJo.put("username",record.getPhone());
                sunJo.put("password",record.getPassword());
                hximService.registerUser(sunJo.toJSONString());
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
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

    @Override
    public User getUserByPoneAndPassword(String phone) {
        return userDao.getUserByPoneAndPassword(phone);
    }

    public int updateUser(User newUser) {
        int i = userDao.updateUser(newUser);
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


}