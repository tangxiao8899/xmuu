package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.service.HximService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hx")
public class HxIMController {

    @Autowired
    HximService hximService;

    /**
     * 获取微信预付单
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


}
