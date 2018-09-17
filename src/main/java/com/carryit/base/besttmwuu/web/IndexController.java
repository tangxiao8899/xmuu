package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.Page;
import com.bean.req.BoardReq;
import com.bean.req.PageParam;
import com.bean.resp.BoardResp;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.UserDTO;
import com.carryit.base.besttmwuu.service.BoardService;
import com.carryit.base.besttmwuu.service.WealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private WealthService wealthService;

    @Autowired
    BoardService boardService;

    //每周上榜
    @RequestMapping(value = "/onTheList", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject onTheList(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());

        long startTime = 0;
        long endTime = 0;
        try {
            startTime = sdf.parse(imptimeBegin + " 00:00:00").getTime();
            endTime = sdf.parse(imptimeEnd + " 23:59:59").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (cmd) {
            case 0:
                List<Board> boardList = new ArrayList<>();
                List<UserDTO> userList = new ArrayList<>();
                BoardResp br = new BoardResp();
                try {
                    //查每周财富值前六名
                    userList = wealthService.onTheList(startTime, endTime);
                    //查所有的小圈子
                    boardList = boardService.getAllBoard();
                    br.setBoardList(boardList);
                    br.setUserList(userList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doObjResp(br);
            case 1:

                List<UserDTO> list = new ArrayList<>();
                try {
                    list = wealthService.queryPage(startTime, endTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doArraysResp(list);
        }

        return null;
    }



//点击更多，返回100个财富榜
    @RequestMapping(value = "/queryAllWealth", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject queryPage(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }
}
