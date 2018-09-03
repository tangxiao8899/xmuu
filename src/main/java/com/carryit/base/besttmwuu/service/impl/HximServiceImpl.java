package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.service.HximService;
import com.util.JerseyClientUtil;
import com.util.PropertyUtil;
import org.springframework.stereotype.Service;

@Service
public class HximServiceImpl implements HximService {

    @Override
    public ResultPojo getToken() throws Exception{
        String URL = "https://" + PropertyUtil.getProperty("hxim.orgName") + "/"
                + PropertyUtil.getProperty("hxim.appName") + "/token";


        JSONObject param = new JSONObject();
        param.put("grant_type","client_credentials");
        param.put("client_id",PropertyUtil.getProperty("hxim.clientId"));
        param.put("client_secret",PropertyUtil.getProperty("hxim.clientSecret"));

        return JerseyClientUtil.postMethod(param.toString());

    }


}
