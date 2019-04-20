package com.carryit.base.besttmwuu.service;

import com.base.ResultPojo;
import com.bean.req.FriendsReq;

public interface TFriendsService {

    /**
     * 保存数据
     * @param friends TFriends实体
     */
    ResultPojo save(FriendsReq friends);

    /**
     * 修改数据
     * @param friends TFriends实体
     */
    ResultPojo update(FriendsReq friends);

    /**
     * 获取好友列表
     * @param json 请求参数  uid  pageIndex pageSize
     * @return
     */
    ResultPojo getFriends(String json);

    /**
     * 统计好友请求数
     * @param json uid 用户ID
     * @return
     */
    ResultPojo totalFriends(String json);

    boolean isFriends(String uid,String mid);


}
