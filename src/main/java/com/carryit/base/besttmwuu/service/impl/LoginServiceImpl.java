package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bean.LoginReq;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.LoginService;
import com.carryit.base.besttmwuu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Override
    public JSONObject login(LoginReq req) {

        JSONObject jo = new JSONObject();

        String phone = req.getPhone();
        String password = req.getPassword();

        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){
            jo.put("code",400);
            jo.put("message","请求参数异常");
            jo.put("data","");
            return jo;
        }

        User user = userService.selectByPhone(phone);
        if(user == null){ //用户不存在
            jo.put("code",401);
            jo.put("message","用户不存在");
            jo.put("data","");
            return jo;
        }else if(!password.equals(user.getPassword())){ //密码错误
            jo.put("code",401);
            jo.put("message","密码错误");
            jo.put("data","");
            return jo;
        }else{ //登录成功
            JSONObject data = new JSONObject();
            data.put("token",user.getId());
            data.put("id",user.getId());
            jo.put("code",200);
            jo.put("message","登录成功");
            jo.put("data",data);
            return jo;
        }
    }
}
