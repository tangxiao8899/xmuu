package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String txLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone=req.getParameter("phone");
        String password=req.getParameter("password");
        if(StringUtils.isBlank(phone)||StringUtils.isBlank(password)){
            return "账号或者密码不能为空";
        }
        User user=new User();
        user=userService.getUserByPoneAndPassword(phone);
        if(password.equals(user.getPassword())){
            return "密码错误";
        }else {
            return "登录成功";
        }
    }
}
