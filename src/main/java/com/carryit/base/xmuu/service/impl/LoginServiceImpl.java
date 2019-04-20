package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bean.LoginReq;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.MemberData;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.LoginService;
import com.carryit.base.besttmwuu.service.MemberService;
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
    MemberService memberService;

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

                        User user = userService.getUserById(Integer.valueOf(key.split("_")[0]));
                        data.put("phone",user.getPhone()==null?"":user.getPhone());
                        data.put("idCard",user.getIdCard()==null?"":user.getIdCard());
                        data.put("username",user.getUserName()==null?"":user.getUserName());
                        data.put("address",user.getAddress()==null?"":user.getAddress());
                        data.put("age",user.getAge()==null?"":user.getAge());
                        data.put("password",user.getPassword()==null?"":user.getPassword());
                        data.put("companyProfile",user.getCompanyProfile()==null?"":user.getCompanyProfile());
                        data.put("corporateName",user.getCorporateName()==null?"":user.getCorporateName());
                        data.put("education",user.getEducation()==null?"":user.getEducation());
                        data.put("mailbox",user.getMailbox()==null?"":user.getMailbox());
                        data.put("marriage",user.getMarriage()==null?"":user.getMarriage());
                        data.put("need",user.getNeed()==null?"":user.getNeed());
                        data.put("services",user.getServices()==null?"":user.getServices());
                        data.put("sex",user.getSex()==null?"":user.getSex());
                        MemberData m = memberService.getMemberDataByUId(Integer.valueOf(key.split("_")[0]));

                        if(!StringUtils.isEmpty(m)){
                            data.put("level",m.getLevel());
                            data.put("nickName",m.getNickName()==null?"":m.getNickName());
                            data.put("autograph",m.getAutograph()==null?"":m.getAutograph());
                            data.put("avatar",m.getAvatar()==null?"":m.getAvatar());
                        }

                        data.put("hxu",key.split("_")[1]);
                        data.put("hxp",key.split("_")[2]);
                        jo.put("code",200);
                        jo.put("msg","登录成功");
                        jo.put("data",data);
                        return jo;
                    }else{
                        //token错误，重新登录
                        jo.put("code",403);
                        jo.put("msg","token错误，请重新登录");
                        jo.put("data",null);
                        return jo;
                    }
                }else{ //不存在token 说明token过期
                    //重新登录
                    jo.put("code",403);
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

                String k = user.getUid()+"_"+ user.getPhone()+"_"+user.getPassword()+"_" + System.currentTimeMillis();
                Cookie cookie = new Cookie("token",k);
                cookie.setMaxAge(10*24*60*60); //设置token有效期为10天
                cookie.setPath("/");
                response.addCookie(cookie);
                data.put("token",k);
                data.put("uid",user.getUid());
                data.put("phone",user.getPhone()==null?"":user.getPhone());
                data.put("idCard",user.getIdCard()==null?"":user.getIdCard());
                data.put("username",user.getUserName()==null?"":user.getUserName());
                data.put("address",user.getAddress()==null?"":user.getAddress());
                data.put("age",user.getAge()==null?"":user.getAge());
                data.put("password",user.getPassword()==null?"":user.getPassword());
                data.put("companyProfile",user.getCompanyProfile()==null?"":user.getCompanyProfile());
                data.put("corporateName",user.getCorporateName()==null?"":user.getCorporateName());
                data.put("education",user.getEducation()==null?"":user.getEducation());
                data.put("mailbox",user.getMailbox()==null?"":user.getMailbox());
                data.put("marriage",user.getMarriage()==null?"":user.getMarriage());
                data.put("need",user.getNeed()==null?"":user.getNeed());
                data.put("services",user.getServices()==null?"":user.getServices());
                data.put("sex",user.getSex()==null?"":user.getSex());

                MemberData m = memberService.getMemberDataByUId(user.getUid());

               if(!StringUtils.isEmpty(m)){
                   data.put("level",m.getLevel());
                   data.put("nickName",m.getNickName()==null?"":m.getNickName());
                   data.put("autograph",m.getAutograph()==null?"":m.getAutograph());
                   data.put("avatar",m.getAvatar()==null?"":m.getAvatar());
               }
                data.put("hxu",user.getPhone());
                data.put("hxp",user.getPassword());
                jo.put("code",200);
                jo.put("msg","登录成功");
                jo.put("data",data);
                return jo;
            }
        }
        return jo;
    }
}
