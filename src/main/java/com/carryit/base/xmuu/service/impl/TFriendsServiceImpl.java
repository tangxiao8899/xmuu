package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.bean.req.FriendsReq;
import com.carryit.base.besttmwuu.dao.TFriendsDao;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.TFriends;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.MemberService;
import com.carryit.base.besttmwuu.service.TFriendsService;
import com.carryit.base.besttmwuu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class TFriendsServiceImpl implements TFriendsService {

    @Autowired
    private TFriendsDao dao;

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    @Override
    public ResultPojo save(FriendsReq friends) {
        ResultPojo rp = new ResultPojo();

        //通过手机号，获取用户头像昵称
        String phone = friends.getToAddUsername();

        Member m = memberService.getMemberByPhone(phone);

        if (StringUtils.isEmpty(m)) {
            rp.setCode(404);
            rp.setMsg("用户名为" + phone + "的好友不存在");
            rp.setData("");
        }  else {
            //匹配数据库看有没有数据
            FriendsReq friendsReq=dao.getAllByUidPhone(phone,friends.getUid());
            if (StringUtils.isEmpty(friendsReq)){
                TFriends f = new TFriends();
                f.setToAddUsername(phone);
                f.setAvatar(m.getAvatar());
                f.setNickName(m.getNickName());
                f.setProcessingTime(new Date());
                f.setUid(friends.getUid());
                f.setState(2); //申请时  默认是待审核状态
                dao.save(f);
                rp.setCode(200);
                rp.setMsg("申请发送成功");
                rp.setData("");
            }
            else {
                rp.setCode(200);
                rp.setMsg("已经添加好友,请等待通过");
                rp.setData("");
            }
        }
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
        if (StringUtils.isEmpty(json)) {
            rp.setData("");
            rp.setMsg("请求参数不能为空");
            rp.setCode(400);

        } else {
            JSONObject subJo = JSON.parseObject(json);
            String phone = subJo.getString("phone");
            int pageSize = Integer.valueOf(subJo.getString("pageSize")); //每页数量
            int pageIndex = Integer.valueOf(subJo.getString("pageIndex")); //当前页

            int index = pageSize*(pageIndex-1);

            List<TFriends> list = dao.getFriends(phone, index, pageSize);
            rp.setData(list == null ? "" : list);
            rp.setMsg("请求成功");
            rp.setCode(200);

        }

        return rp;
    }

    @Override
    public ResultPojo totalFriends(String json) {
        ResultPojo rp = new ResultPojo();
        if (StringUtils.isEmpty(json)) {
            rp.setData("");
            rp.setMsg("请求参数不能为空");
            rp.setCode(400);
        } else {
            JSONObject subJo = JSON.parseObject(json);
            String phone = subJo.getString("phone");
            int totalFriends = dao.totalFriends(phone);
            rp.setData(totalFriends);
            rp.setMsg("获取好友申请数成功");
            rp.setCode(200);
        }
        return rp;
    }

    @Override
    public boolean isFriends(String loginUid, String showUid) {

        User user = userService.getUserById(Integer.parseInt(showUid));
        TFriends tf = dao.isFriends(loginUid, user.getPhone());
        if (loginUid.equals(showUid)){
            return true;
        }
        if (tf == null) {
            return false;
        } else {
            return true;
        }
    }
}