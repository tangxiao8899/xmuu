package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bean.LoginReq;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.LoginService;
import com.carryit.base.besttmwuu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Override
    public JSONObject login(LoginReq req) {

        JSONObject jo = new JSONObject();

        String phone = req.getPhone();
        String password = req.getPassword();
        String token = req.getToken();

        if(!StringUtils.isEmpty(token)){
            //自动登录
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            String cookie=request.getHeader("cookie");
            Cookie[] cookies = request.getCookies();
            if(cookies == null || cookies.length == 0){
                jo.put("code",401);
                jo.put("msg","首次登陆，请输入用户密码！");
                jo.put("data",null);
                return jo;
            }

            for(Cookie c : cookies){
                if(c.getName().equals("token")){ //存在token，校验token正确性
                    String key = c.getValue();
                    if(token.equals(key)){
                        JSONObject data = new JSONObject();
                        data.put("token",key);
                        data.put("uid",key.split("_")[0]);
                        jo.put("code",200);
                        jo.put("msg","登录成功");
                        jo.put("data",data);
                        return jo;
                    }else{
                        //token错误，重新登录
                        jo.put("code",401);
                        jo.put("msg","token错误，请重新登录");
                        jo.put("data",null);
                        return jo;
                    }
                }else{ //不存在token 说明token过期
                    //重新登录
                    jo.put("code",401);
                    jo.put("msg","token过期，请重新登录");
                    jo.put("data",null);
                    return jo;
                }
            }
        }else{
            if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){
                jo.put("code",400);
                jo.put("msg","请求参数异常");
                jo.put("data",null);
                return jo;
            }
            User user = userService.selectByPhone(phone);
            if(user == null){ //用户不存在
                jo.put("code",401);
                jo.put("msg","用户不存在");
                jo.put("data",null);
                return jo;
            }else if(!password.equals(user.getPassword())){ //密码错误
                jo.put("code",401);
                jo.put("msg","密码错误");
                jo.put("data",null);
                return jo;
            }else{ //登录成功
                JSONObject data = new JSONObject();

                String k = user.getUid()+"_"+ user.getPhone() + System.currentTimeMillis();
                Cookie cookie = new Cookie("token",k);
                cookie.setMaxAge(10*24*60*60); //设置token有效期为10天
                cookie.setPath("/");
                response.addCookie(cookie);
                data.put("token",k);
                data.put("uid",user.getUid());
                jo.put("code",200);
                jo.put("msg","登录成功");
                jo.put("data",data);
                return jo;
            }
        }
        return jo;
    }
}
