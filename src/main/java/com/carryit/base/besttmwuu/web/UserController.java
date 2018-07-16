package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/showUser")
    public User toIndex(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        return user;
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Map<String, Object> addUser(User user) {
        Map<String,Object> param = new HashMap<String,Object>();
        boolean flg = userService.addUser(user);
        if (flg){
            param.put("success", "成功");
        }else {
            param.put("error", "失败");
        }
        return param;
    }
}
