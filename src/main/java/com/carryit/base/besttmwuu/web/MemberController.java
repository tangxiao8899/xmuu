package com.carryit.base.besttmwuu.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ServiceConfig;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.dao.TFriendsDao;
import com.carryit.base.besttmwuu.entity.*;
import com.carryit.base.besttmwuu.service.*;
import com.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private GlobounService globounService;

    @Autowired
    private SincerityService sincerityService;

    @Autowired
    private TFriendsService tFriendsService;

    //用户中心
    @RequestMapping(value = "/member", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject Member(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }


    //财富值
    @RequestMapping(value = "/wealth", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject Wealth(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }

    //诚信值
    @RequestMapping(value = "/sincerity", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject Sincerity(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 2);
    }

    //我的资产
    @RequestMapping(value = "/globouns", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject Globouns(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 3);
    }

    //用户中心
    @RequestMapping(value = "/getMember", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getMember(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 4);
    }

    //点击我的头像获取资料
    @RequestMapping(value = "/getMemberByUid", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getMemberByUid(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 5);
    }

    //点击我的头像获取资料
    @RequestMapping(value = "/updateMemberByUid", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject updateMemberByUid(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 6);
    }



    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                Member member=new Member();
                try {
                    BoardReq req = p(json, BoardReq.class);
                    if(req!=null){
                        member= memberService.getMemberById(req.uid);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
                return doObjResp(member);
            case 1:
                Member mem =new Member();
                float Wealth =0;

                try {
                    BoardReq req = p(json, BoardReq.class);
                    if (req != null) {
                        mem=memberService.getWealthById(req.uid);
                        if(mem==null){
                            return faild("用户不存在~", false);
                        }
                        Wealth=mem.getCredit2();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
                return doObjResp(Wealth);
            case 2:
                Sincerity sincerity=new Sincerity();
                try {
                    BoardReq req = p(json, BoardReq.class);
                    if(req!=null){
                        sincerity=sincerityService.getNumberById(req.uid);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
                return doObjResp(sincerity);
            case 3:
                Globouns globouns=new Globouns();
                try {
                    BoardReq req = p(json, BoardReq.class);
                    if(req!=null){
                        globouns=globounService.getFindAll(req.uid);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
                return doObjResp(globouns);
            case 4:
                MemberDTO mb=new MemberDTO();
                try {
                    JSONObject subJo = JSON.parseObject(json);
                    String loginUid = subJo.getString("loginUid");
                    String showUid = subJo.getString("showUid");
                    if (!StringUtils.isEmpty(loginUid) && !StringUtils.isEmpty(showUid)) {

                        mb= memberService.showMember(Integer.parseInt(loginUid));
                        boolean friends = tFriendsService.isFriends(loginUid, showUid);
                        mb.setFriends(friends+"");

                    } else {
                        return faild("参数异常~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
                return doObjResp(mb);
            case 5:
                MemberData md=new MemberData();
                try {
                    BoardReq req = p(json, BoardReq.class);
                    if(req!=null){
                        md= memberService.getMemberDataByUId(req.uid);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
                return doObjResp(md);
            case 6:

            try {
                MemberData req = p(json, MemberData.class);
                if(req!=null){
                    memberService.updateMemberDataByUId(req);
                    return doObjRespSuccess("更新成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return faild("失败~", false);
            }


        }
        return null;
    }

}
