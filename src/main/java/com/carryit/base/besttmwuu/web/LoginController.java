package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.LoginReq;
import com.carryit.base.besttmwuu.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    /**
     * 用户登录
     * @param json
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject login(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd){
            case 0:
                LoginReq req = p(json, LoginReq.class);
                if (req != null) {
                    try{
                        return loginService.login(req);
                    } catch (Exception e){
                        e.printStackTrace();
                        logger.error(e.getMessage());
                        return doObjResp(false,-999,"程序异常!");
                    }
                } else {
                    return faild("请求参数异常~", false);
                }
        }
        return null;
    }
}
