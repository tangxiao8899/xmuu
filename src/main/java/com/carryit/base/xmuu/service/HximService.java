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

    /**
     * IM添加好友|删除好友
     * @param json
     * @return
     * @throws Exception
     */
    ResultPojo addFriends(String json) throws Exception;


    /**
     * 获取 IM 用户的好友列表 | 黑名单
     * @param json
     * @return
     * @throws Exception
     */
    ResultPojo getFriends(String json) throws Exception;

    /**
     * 查看用户在线状态
     * @param json
     * @return
     * @throws Exception
     */
    ResultPojo getUserStatus(String json) throws Exception;

    /**
     * 查询离线消息数
     * @param json
     * @return
     * @throws Exception
     */
    ResultPojo offlineMsgCount(String json) throws Exception;

    /**
     * 发送文本消息
     * @param json
     * @return
     * @throws Exception
     */
    ResultPojo sendMessages(String json) throws Exception;

    /**
     * 重置IM用户密码
     * @param json
     * @return
     * @throws Exception
     */
    ResultPojo resetPassword(String json)throws Exception;

    /**
     * 获取用户 -单个用户、批量用户
     * @param json
     * @return
     * @throws Exception
     */
    ResultPojo getUser(String json)throws Exception;
}
