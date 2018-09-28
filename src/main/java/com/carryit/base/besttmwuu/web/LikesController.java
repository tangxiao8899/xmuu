package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.Sincerity;
import com.carryit.base.besttmwuu.entity.UserPost;
import com.carryit.base.besttmwuu.service.LikeService;
import com.carryit.base.besttmwuu.service.MemberService;
import com.carryit.base.besttmwuu.service.SincerityService;
import com.carryit.base.besttmwuu.service.UserPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikesController extends BaseController {

    @Autowired
    private LikeService likeService;
    @Autowired
    private UserPostService userPostService;
    Logger logger = LoggerFactory.getLogger(LikesController.class);
    //签到接口
    @RequestMapping(value = "/likes", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject likes(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    //点赞接口
    @RequestMapping(value = "/fabulous", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject fabulous(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                BoardReq req = p(json, BoardReq.class);
                if (req!=null) {
                    likeService.saveCreditNumber(req.uid);
                } else {
                    return faild("失败~", false);
                }
            case 1:
                BoardReq breq = p(json, BoardReq.class);
                if(breq!=null){
                    userPostService.updateFabulousByUid(breq.uid);
                }else {
                    return faild("失败~", false);
                }

        }
        return null;
    }
}
