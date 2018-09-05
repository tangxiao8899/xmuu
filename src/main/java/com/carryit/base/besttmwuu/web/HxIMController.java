package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.HximService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.base.BaseController.p;

@RestController
@RequestMapping("/hx")
public class HxIMController {

    @Autowired
    HximService hximService;

    /**
     * 获取环信TOKEN
     * @param json
     * @return
     */
    @RequestMapping(value = "/getToken", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo getWxPay(@RequestBody(required = false) String json) {
        try {
            return  hximService.getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 授权注册--单个用户|批量用户
     * @param json
     * @return
     */
    @RequestMapping(value = "/registerUser", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo registerUser(@RequestBody(required = false) String json) {
        try {
            return  hximService.registerUser(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
