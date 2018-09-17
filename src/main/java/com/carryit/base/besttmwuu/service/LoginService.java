package com.carryit.base.besttmwuu.service;

import com.alibaba.fastjson.JSONObject;
import com.bean.LoginReq;

public interface LoginService {

    /**
     * 用户登录
     * @param req
     * @return
     */
    JSONObject login(LoginReq req);
}
