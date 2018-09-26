package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ServiceConfig;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.*;
import com.carryit.base.besttmwuu.service.*;
import com.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/circle")
public class CirclesController extends BaseController {

    Logger logger = LoggerFactory.getLogger(CirclesController.class);

    @Autowired
    CirclesService circlesService;
    @Autowired
    BoardService boardService;
    @Autowired
    BoardFollowService boardFollowService;
    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/getCircles", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getCircles(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    /*
     * 根据用户id,圈子id获取圈子信息，并且判断用户是否关注圈子
     */
    @RequestMapping(value = "/getBoardByUid", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getBoardByUid(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                List<Circles> data = new ArrayList<>();
                try {
                    data = circlesService.getCircles();
                    if (data != null && data.size() > 0) {
                        for (Circles circle : data) {
                            List<Board> boardList = boardService.getBoardByCid(circle.getId());
                            circle.setBoards(boardList);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doArraysResp(data);
            case 1:
                Board board = new Board();

                BoardFollow bf = null;

                try {
                    BoardReq req = p(json, BoardReq.class);
                    if (req == null) {
                        if (ServiceConfig.DEBUG) {
                            req = new BoardReq();
                            req.bid = 17;
                            req.uid = 17;
                        }
                    }
                    Log.e("请求字符串=" + json + "|命令|" + cmd);
                    board = boardService.getBoardById(req.bid);// 查询圈子详细信息
                    bf = boardFollowService.getBoardByUid(req.uid, req.bid);// 查询该用户是否关注该圈子
                    if (bf != null) {
                        board.setFollow(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doObjResp(board);
            case 2:
                List<Member> memberList = new ArrayList<>();

                BoardReq req = p(json, BoardReq.class);
                try {
                    if(req!=null){
                        Member member = memberService.getMemberById(req.uid);
                        if(member!=null&&member.getZhuquanzi()!=null){
                            memberList = boardFollowService.getMemberByZhuQuanZiId(member.getZhuquanzi());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doObjResp(memberList);
            case 3:
                List<Board> boardList = new ArrayList<>();

                BoardReq _req = p(json, BoardReq.class);
                try {
                    if(_req!=null){
                        boardList = boardFollowService.getBoardFollowByUId(_req.uid);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doObjResp(boardList);
            case 4:
                List<Board> newboardList = new ArrayList<>();

                BoardReq unconcerned = p(json, BoardReq.class);
                try {
                    if(unconcerned!=null){
                        newboardList = boardFollowService.getUnconcerned(unconcerned.uid);
                        if (newboardList!=null&&newboardList.size()>0) {
                            if(newboardList.size()>10){
                                newboardList = newboardList.subList(0,10);
                            }
                          }
                        }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doObjResp(newboardList);
        }
        return null;
    }

    /*
    * 关注与取消关注
    * */
    @RequestMapping("/onAndOff")
    public Map<String, String> onAndOff(int uid, int bid, String follow) {

        Map<String, String> map = new HashMap<>();
        map.put("code", "201");
        map.put("msg", "操作失败");
        try {
            if (uid != 0 & bid != 0 & follow != null) {
                if ("0".equals(follow)) {
                    //取消关注
                    boardFollowService.delete(uid, bid);
                    map.put("code", "200");
                    map.put("msg", "操作成功");
                } else if ("1".equals(follow)) {
                    //关注

                    boardFollowService.add(uid, bid, new Date().getTime());
                    map.put("code", "200");
                    map.put("msg", "操作成功");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }

    /*
    圈子管理
    * 根据用户id获取主圈子和主圈子下面的所有会员
    * */
    @RequestMapping(value = "/getZhuQuanZiByUid", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getZhuQuanZiByUid(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 2);
    }

    /*
    *
    * 根据用户id获取关注过的圈子（收藏）
    * */
    @RequestMapping(value = "/getQuanZiByUid", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getQuanZiByUid(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 3);
    }

    /*
  *
  * 推荐10条动态最高的未关注的圈子
  * */
    @RequestMapping(value = "/getUnconcerned", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getUnconcerned(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 4);
    }

}
