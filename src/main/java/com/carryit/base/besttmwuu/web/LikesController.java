package com.carryit.base.besttmwuu.web;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.BoardReq;
import com.bean.req.CommentReq;
import com.bean.req.PraiseReq;
import com.carryit.base.besttmwuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.besttmwuu.entity.TPraise;
import com.carryit.base.besttmwuu.service.ImsEweiShopSnsPostService;
import com.carryit.base.besttmwuu.service.LikeService;
import com.carryit.base.besttmwuu.service.PraiseService;
import com.carryit.base.besttmwuu.service.SignInService;
import com.carryit.base.besttmwuu.service.UserPostService;

@RestController
@RequestMapping("/likes")
public class LikesController extends BaseController {

    @Autowired
    private LikeService likeService;
    @Autowired
    private UserPostService userPostService;
    Logger logger = LoggerFactory.getLogger(LikesController.class);
    
    //处理签到服务
    @Autowired
    SignInService signInService;
    
//    点赞服务
    @Autowired
    PraiseService praiseService;
    
//    保存评论服务
    @Autowired
    ImsEweiShopSnsPostService postService;
    
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
    
    //评论接口
    @RequestMapping(value = "/comment", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject comment(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 3);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                BoardReq req = p(json, BoardReq.class);
                if (req!=null) {
//                    likeService.saveCreditNumber(req.uid);
//                	签到
                	signInService.sign(req.uid, LocalDateTime.now());
                	return doObjRespSuccess("签到成功");
                } else {
                    return faild("失败~", false);
                }
            case 1:
            	PraiseReq pReq = p(json, PraiseReq.class);
                if(pReq!=null){
//                    userPostService.updateFabulousByUid(breq.uid);
                	TPraise praise = new TPraise();
                	praise.setUid(pReq.getUid());
                	praise.setImsEweiShopSnsPostId(pReq.getId());
                	praise.setCreatetime(new Date());
//                	点赞
                	praiseService.praise(praise);
                	return doObjRespSuccess("点赞成功");
                }else {
                    return faild("失败~", false);
                }
            case 2:
//            	添加评论
            	CommentReq commentReq = p(json, CommentReq.class);
                if(commentReq!=null){
                	ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs = new ImsEweiShopSnsPostWithBLOBs();
                	imsEweiShopSnsPostWithBLOBs.setPid(commentReq.getPid());
                	imsEweiShopSnsPostWithBLOBs.setUid(commentReq.getUid());
                	imsEweiShopSnsPostWithBLOBs.setBid(commentReq.getBid());
                	imsEweiShopSnsPostWithBLOBs.setContent(commentReq.getContent());
//                	保存
                	postService.addOne(imsEweiShopSnsPostWithBLOBs);
                	return doObjRespSuccess("点赞成功");
                }else {
                    return faild("失败~", false);
                }

        }
        return null;
    }
}
