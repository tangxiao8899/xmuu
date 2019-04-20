package com.carryit.base.besttmwuu.service;

import com.alibaba.fastjson.JSONObject;
import com.carryit.base.besttmwuu.entity.MessageCode;

public interface MessageCodeService {
    String getByPhone(String phoneNumber);

    void insert(MessageCode messageCode);

    void update(MessageCode messageCode);

    MessageCode getIdByPhone(String phoneNumber);

    //保存短信验证码信息
    void saveMessageCode(String phoneNumber,int code);

    //校验短信验证码
    JSONObject checkCode(String json);
}
