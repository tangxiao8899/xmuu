package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.Page;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.Activity;
import com.carryit.base.besttmwuu.entity.UserLevel;
import com.carryit.base.besttmwuu.service.ActivityService;
import com.carryit.base.besttmwuu.service.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;


    //发布活动
    @RequestMapping(value = "/release", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject add(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    //分页查询全部活动
    @RequestMapping(value = "/releasePage", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject releasePage(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }

    //根据活动id,查询活动具体信息
    @RequestMapping(value = "/getReleaseById", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getReleaseById(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 2);
    }


    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd){
            case 0:
                try {
                    Activity activity = p(json, Activity.class);
                    activityService.add(activity);
                     return doObjRespSuccess("发布成功");
                }catch (Exception e){
                    e.printStackTrace();
                     return faild("发布失败~",false);
                }
            case 1:
                try {
                    JSONObject jo = JSON.parseObject(json);
                    int pageStart = jo.getInteger("pageStart");
                    int pageSize = jo.getInteger("pageSize");

                    List<Activity> activityList = activityService.getPage((pageStart - 1) * pageSize,pageSize);
                    long count = activityService.getPageCount();
                    Page newpage = new Page();
                    newpage.setList(activityList);
                    newpage.setPageSize(pageSize);
                    newpage.setTotalSize(count);
                    return doObjResp(newpage);
                }catch (Exception e){
                    e.printStackTrace();
                    return faild("失败~",false);
                }
            case 2:
                try {
                    JSONObject jo = JSON.parseObject(json);
                    int pageStart = jo.getInteger("pageStart");
                    int pageSize = jo.getInteger("pageSize");

                    List<Activity> activityList = activityService.getPage((pageStart - 1) * pageSize,pageSize);
                    long count = activityService.getPageCount();
                    Page newpage = new Page();
                    newpage.setList(activityList);
                    newpage.setPageSize(pageSize);
                    newpage.setTotalSize(count);
                    return doObjResp(newpage);
                }catch (Exception e){
                    e.printStackTrace();
                    return faild("失败~",false);
                }
        }
        return null;
    }
}
