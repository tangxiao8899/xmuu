package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.bean.req.FriendsReq;
import com.carryit.base.besttmwuu.dao.TFriendsDao;
import com.carryit.base.besttmwuu.entity.TFriends;
import com.carryit.base.besttmwuu.service.TFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TFriendsServiceImpl implements TFriendsService {

    @Autowired
    private TFriendsDao dao;

    @Override
    public ResultPojo save(FriendsReq friends) {
        ResultPojo rp = new ResultPojo();

        TFriends f = new TFriends();
        f.setAvatar(friends.getAvatar());
        f.setNickName(friends.getNickName());
        f.setProcessingTime(friends.getProcessingTime());
        f.setUid(friends.getUid());
        f.setState(2); //申请时  默认是待审核状态
        dao.save(f);
        rp.setCode(200);
        rp.setMsg("申请发送成功");
        rp.setData("");

        return rp;
    }

    @Override
    public ResultPojo update(FriendsReq friends) {
        ResultPojo rp = new ResultPojo();

        TFriends f = new TFriends();
        f.setId(friends.getId());
        f.setState(friends.getState());

        dao.update(f);
        rp.setCode(200);
        rp.setMsg("操作成功");
        rp.setData("");

        return rp;

    }

    @Override
    public ResultPojo getFriends(String json) {
        ResultPojo rp = new ResultPojo();
        if(StringUtils.isEmpty(json)){
            rp.setData("");
            rp.setMsg("请求参数不能为空");
            rp.setCode(400);

        }else{
            JSONObject subJo = JSON.parseObject(json);
            String uid = subJo.getString("title");
            int pageSize = Integer.valueOf(subJo.getString("pageSize")); //每页数量
            int pageIndex = Integer.valueOf(subJo.getString("pageIndex")); //当前页

            int index = pageIndex >1 ? pageSize * (pageIndex-1) +1 : pageIndex;
            int size = index == 0 ? pageSize: pageIndex * pageSize;

            List<TFriends> list = dao.getFriends(Integer.valueOf(uid),index,size);
            rp.setData(list == null ? "":list);
            rp.setMsg("请求成功");
            rp.setCode(200);

        }

        return rp;
    }

    @Override
    public ResultPojo totalFriends(String json) {
        ResultPojo rp = new ResultPojo();
        if(StringUtils.isEmpty(json)){
            rp.setData("");
            rp.setMsg("请求参数不能为空");
            rp.setCode(400);
        }else{
            JSONObject subJo = JSON.parseObject(json);
            String uid = subJo.getString("uid");
            int totalFriends = dao.totalFriends(Integer.valueOf(uid));

            rp.setData(totalFriends);
            rp.setMsg("获取好友申请数成功");
            rp.setCode(200);
        }
        return rp;
    }
}
