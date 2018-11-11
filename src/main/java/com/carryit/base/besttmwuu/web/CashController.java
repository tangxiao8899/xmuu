package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cashLogin")
public class CashController extends HttpServlet {
    @Autowired
    private UserService userService;
    Logger logger = LoggerFactory.getLogger(CashController.class);
    @RequestMapping(value = "/login")
    public String cashLogin(){
        logger.info("=====");
        return "login";
    }

    @RequestMapping(value = "/txLogin")
    @ResponseBody
    public JSONObject txLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jo = new JSONObject();
        String phone=req.getParameter("phone");
        String password=req.getParameter("password");
        if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){
            jo.put("code",400);
            jo.put("msg","账号或者密码不能为空");
        }
        User user = userService.getUserByPoneAndPassword(phone);
        if(StringUtils.isEmpty(user)){
            jo.put("code",404);
            jo.put("msg","用户不存在");
        }else if(!password.equals(user.getPassword())){
            jo.put("code",401);
            jo.put("msg","密码不正确");
        }else {
            jo.put("code",200);
            jo.put("msg","登录成功");
        }
        return jo;
    }

    @RequestMapping(value = "/index")
    public String index(){
        logger.info("=====");
        return "index";
    }
}
