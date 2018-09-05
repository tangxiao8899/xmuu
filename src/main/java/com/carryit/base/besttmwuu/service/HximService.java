package com.carryit.base.besttmwuu.service;

import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.entity.User;

public interface HximService {

    /**
     * 获取环信授权TOKEN
     * @return
     * @throws Exception
     */
    ResultPojo getToken() throws Exception;

    /**
     * 授权注册用户
     * @param json 请求参数  json格式
     * @return
     * @throws Exception
     */
    ResultPojo registerUser(String json) throws Exception;
}
