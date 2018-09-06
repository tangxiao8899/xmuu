package com.carryit.base.besttmwuu.web;


import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ServiceConfig;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.*;
import com.carryit.base.besttmwuu.service.GlobounService;
import com.carryit.base.besttmwuu.service.MemberService;
import com.carryit.base.besttmwuu.service.SincerityService;
import com.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "sincerity", method = {RequestMethod.GET,
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
                }
                return doObjResp(member);
            case 1:
                Member mem =new Member();
                String Wealth= null;

                try {
                    BoardReq req = p(json, BoardReq.class);
                    if (req != null) {
                        mem=memberService.getWealthById(req.uid);
                        Wealth=mem.getCredit2();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                }
                return doObjResp(globouns);
        }
        return null;
    }

}
