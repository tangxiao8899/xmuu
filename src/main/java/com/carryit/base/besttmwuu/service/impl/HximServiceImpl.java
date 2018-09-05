package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.HximService;
import com.util.JerseyClientUtil;
import com.util.PropertyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HximServiceImpl implements HximService {

    @Override
    public ResultPojo getToken() throws Exception{


        JSONObject param = new JSONObject();
        param.put("grant_type","client_credentials");
        param.put("client_id",PropertyUtil.getProperty("hxim.clientId"));
        param.put("client_secret",PropertyUtil.getProperty("hxim.clientSecret"));

        return JerseyClientUtil.postMethod(param.toString(),"/token");

    }

    @Override
    public ResultPojo registerUser(String json) throws Exception {
        JSONObject jo = JSON.parseObject(json);
        //校验授权信息
        if(!jo.containsKey("token")){
            ResultPojo rp = new ResultPojo();
            rp.setStatus(401);
            rp.setErrorMsg("请先获取授权信息");
            return rp;
        }
        if(!jo.containsKey("username") || !jo.containsKey("password")){
            ResultPojo rp = new ResultPojo();
            rp.setStatus(400);
            rp.setErrorMsg("请求参数异常");
            return rp;
        }

        JSONObject reqParam = new JSONObject();
        reqParam.put("username",jo.getString("username"));
        reqParam.put("password",jo.getString("password"));

        return JerseyClientUtil.postMethod(reqParam.toString(),jo.getString("token"),"/users");
    }


}
