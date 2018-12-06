package com.carryit.base.besttmwuu.web;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.bean.Page;
import com.bean.req.TredsReq;
import com.carryit.base.besttmwuu.entity.*;
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
        return callHttpReqTask(json, 2);
    }

    //动态接口
    @RequestMapping(value = "/treds", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject Treds(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 3);
    }

    //查询动态接口
    @RequestMapping(value = "/getTreds", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getTreds(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 4);
    }

    //查询动态接口
    @RequestMapping(value = "/delTreds", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject delTreds(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 5);
    }


    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                try{
                BoardReq req = p(json, BoardReq.class);
                    if (req!=null) {
    //                    likeService.saveCreditNumber(req.uid);
    //                	签到
                        boolean sign = signInService.sign(req.uid, LocalDateTime.now());
                        if(sign){
                            return doObjRespSuccess("签到成功");
                        }else {
                            return faild("您今天已经签到了哟~", false);
                        }

                    } else {
                        return faild("失败，参数异常~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
            case 1:
                try {
                    PraiseReq pReq = p(json, PraiseReq.class);
                    if(pReq!=null&&pReq.id!=0&&pReq.uid!=0){
    //                    userPostService.updateFabulousByUid(breq.uid);
                        if("0".equals(pReq.getFabulous())){
                            praiseService.deletepraise(pReq.id,pReq.uid);
                            return doObjRespSuccess("取消点赞成功");
                        }else if("1".equals(pReq.getFabulous())){
                            TPraise praise = new TPraise();
                            praise.setUid(pReq.getUid());
                            praise.setImsEweiShopSnsPostId(pReq.getId());
                            praise.setCreatetime(new Date());
                            //                	点赞
                            praiseService.praise(praise);
                            return doObjRespSuccess("点赞成功");
                        }

                    }else {
                        return faild("参数异常~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
            case 2:
//            	添加评论
                try {
                    CommentReq commentReq = p(json, CommentReq.class);
                    if(commentReq!=null){
                        ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs = new ImsEweiShopSnsPostWithBLOBs();
                        imsEweiShopSnsPostWithBLOBs.setPid(commentReq.getPid());
                        imsEweiShopSnsPostWithBLOBs.setUid(commentReq.getUid());
                        imsEweiShopSnsPostWithBLOBs.setContent(URLEncoder.encode(commentReq.getContent(), "utf-8"));
                        //保存
                        postService.addOne(imsEweiShopSnsPostWithBLOBs);
                        return doObjRespSuccess("评论成功");
                    }else {
                        return faild("失败~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
            case 3:
//            	添加动态
                try {
                    TredsReq tredsReq = p(json, TredsReq.class);
                    if(tredsReq!=null){
                        ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs = new ImsEweiShopSnsPostWithBLOBs();
                        imsEweiShopSnsPostWithBLOBs.setUid(tredsReq.getUid());
                        imsEweiShopSnsPostWithBLOBs.setBid(tredsReq.getBid());
                        imsEweiShopSnsPostWithBLOBs.setContent(URLEncoder.encode(tredsReq.getContent(), "utf-8"));
                        imsEweiShopSnsPostWithBLOBs.setImages(tredsReq.getImage());
    //                	保存
                        postService.addTreds(imsEweiShopSnsPostWithBLOBs);
                        return doObjRespSuccess("发布成功");
                    }else {
                        return faild("失败~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }

            case 4:
                    //根据uid查询动态
                try {
                    BoardManage tredsReq = p(json, BoardManage.class);
                    List<TrendsData> newTrendsDataList = new ArrayList<>();
                    long count = 0;
                    if(tredsReq!=null){
                        //                	保存
                       List<Post> tredsReqList = postService.getTredsList(tredsReq.getUid(),(tredsReq.getPageStart() - 1) * tredsReq.getPageSize(), tredsReq.getPageSize());
                        if(tredsReqList!=null&&tredsReqList.size()>0){
                            for (Post post:tredsReqList) {
                                TrendsData newTrendsData = new TrendsData();
                                if(post.getContent()!=null){
                                    post.setContent(URLDecoder.decode(post.getContent(), "utf-8"));
                                }
                                //根据动态查评论
                                List<Post> newCommentList = postService.getcommentBypid(post.getId());
                                if(newCommentList!=null&&newCommentList.size()>0){
                                    for (Post po:newCommentList) {
                                        po.setContent(URLDecoder.decode(po.getContent(), "utf-8"));
                                    }
                                }

                                //查找点赞数
                                long newPraiseCount  = praiseService.getPraiseCount(post.getId());
                                //点赞头像
                                List<String> newAvatarList = praiseService.getPraiseImage(post.getId());
                                //查询当前动态当前用户是否点赞
                                TPraise pr = praiseService.getPraise(tredsReq.getUid(),post.getId());
                                if(pr==null){
                                    post.setFabulous(0);//没有点赞
                                }else {
                                    post.setFabulous(1);//已经点赞
                                }
                                newTrendsData.setPost(post);
                                newTrendsData.setCommentList(newCommentList);
                                newTrendsData.setPraiseCount(newPraiseCount);
                                newTrendsData.setAvatarList(newAvatarList);
                                newTrendsDataList.add(newTrendsData);
                                if(post.getCreatetime()!=0){
                                    SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                    String date = fm.format(new Date(post.getCreatetime()));
                                    post.setCreateDate(date);
                                }
                                if(post.getImage()!=null){
                                    List<String> result = Arrays.asList(post.getImage().split(","));
                                    post.setImageList(result);
                                }
                            }
                            count =  postService.getTredsCount(tredsReq.getUid());
                        }
                        Page page = new Page();
                        page.setList(newTrendsDataList);
                        page.setPageSize(tredsReq.getPageSize());
                        page.setTotalSize(count);
                       return doObjResp(page);
                    }else {
                        return faild("参数异常~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }

            case 5:
//            	删除动态
                try {
                    JSONObject parmJo = JSON.parseObject(json);
                    if (!parmJo.containsKey("id")) { //用户ID
                        return faild("参数异常~", false);
                    }

                        postService.delTreds(parmJo.getInteger("id"));
                        return doObjRespSuccess("删除成功");

                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }

        }
        return null;
    }
}
