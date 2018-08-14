package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.Page;
import com.bean.req.BoardReq;
import com.bean.req.PageParam;
import com.carryit.base.besttmwuu.entity.UserDTO;
import com.carryit.base.besttmwuu.service.WealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private WealthService wealthService;
    //每日上榜
    @RequestMapping(value = "/onTheList", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject onTheList(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                //获取昨天零点的时间
                Calendar c1 = Calendar.getInstance();
                c1.set(Calendar.HOUR_OF_DAY,0);
                c1.set(Calendar.MINUTE,0);
                c1.set(Calendar.SECOND,0);
                c1.set(Calendar.MILLISECOND,0);
                c1.add(Calendar.DAY_OF_YEAR,-1);
                long startTime = c1.getTimeInMillis();
                //获取昨天24点的时间
                Calendar c2 = Calendar.getInstance();
                c2.set(Calendar.HOUR_OF_DAY,23);
                c2.set(Calendar.MINUTE,59);
                c2.set(Calendar.SECOND,59);
                c2.set(Calendar.MILLISECOND,0);
                c2.add(Calendar.DAY_OF_YEAR,-1);
                long endTime = c2.getTimeInMillis();

                List<UserDTO> data = new ArrayList<>();
                try {
                    data = wealthService.onTheList(startTime,endTime);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return doArraysResp(data);
            case 1:
                Page<UserDTO> page  = new Page<>();
                List<UserDTO> list = new ArrayList<>();
                long count = 0;
                PageParam req = p(json, PageParam.class);
                if(req!=null){
                    list = wealthService.queryPage((req.getPageStart()-1)*req.getPageSize(),req.getPageSize());
                    count = wealthService.queryPageCount();
                    page.setList(list);
                    page.setPageSize(req.getPageSize());
                    page.setTotalSize(count);
                }
               // return doArraysResp(page);

        }
        return null;
    }

    @RequestMapping(value = "/queryPage", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject queryPage(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }
}
