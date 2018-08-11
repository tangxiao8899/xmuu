package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ServiceConfig;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.BoardFollow;
import com.carryit.base.besttmwuu.entity.Circles;
import com.carryit.base.besttmwuu.service.BoardService;
import com.carryit.base.besttmwuu.service.BoardFollowService;
import com.carryit.base.besttmwuu.service.CirclesService;
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
        }
        return null;
    }

    /*
    * 关注与取消关注
    * */
    @RequestMapping("/onAndOff")
    public Map<String, String> onAndOff(int uid, int bid, String follow) {
        Map<String, String> map = new HashMap<>();
        try {
            if (uid != 0 & bid != 0 & follow != null) {
                if ("0".equals(follow)) {
                    //取消关注
                    boardFollowService.delete(uid, bid);
                    map.put("code", "0");
                } else if ("1".equals(follow)) {
                    //关注

                    boardFollowService.add(uid, bid, new Date().getTime());
                    map.put("code", "1");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }
}
