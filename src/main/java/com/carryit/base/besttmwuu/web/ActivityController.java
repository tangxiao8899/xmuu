package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.Page;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.*;
import com.carryit.base.besttmwuu.service.ActivityService;
import com.carryit.base.besttmwuu.service.MemberService;
import com.carryit.base.besttmwuu.service.UserLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private MemberService memberService;

    public static String zero = "0";   //说明是圈主
    public static String one = "1";   //免费活动(活动未结束,未报名)
    public static String two = "2"; //付费活动(活动未结束,未报名,显示金额)
    public static String three = "3"; //已报名(活动未结束)
    public static String four = "4"; //活动已结束


    //发布活动
    @RequestMapping(value = "/release", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject add(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    //根据活动id,查询活动具体信息
    @RequestMapping(value = "/getReleaseById", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getReleaseById(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }


    //分页查询全部活动
    @RequestMapping(value = "/releasePage", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject releasePage(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 2);
    }


    //活动报名
    @RequestMapping(value = "/signUpRelease", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject signUpRelease(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 3);
    }

    //我的发布
    @RequestMapping(value = "/myRelease", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject myRelease(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 4);
    }

    //我的活动
    @RequestMapping(value = "/myActivity", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject myActivity(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 5);
    }

    //获取报名人的信息
    @RequestMapping(value = "/getSignUpInfo", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getSignUpInfo(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 6);
    }


    public static String longToDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                try {
                    Activity activity = p(json, Activity.class);
                    //发布活动,前端不传圈子id,后台去根据uid去查主圈子

                    Member memberData = memberService.getMemberById(activity.getUid());
                    if (zero.equals(memberData.getLevel())) {
                        activity.setBid(memberData.getZhuquanzi());
                        activityService.add(activity);
                    }
                    return doObjRespSuccess("发布成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("发布失败~", false);
                }
            case 1:
                try {
                    Activity newAct = new Activity();
                    JSONObject jo = JSON.parseObject(json);
                    if (jo != null) {
                        int id = jo.getInteger("aid");
                        int uid = jo.getInteger("uid");
                        if (id != 0) {
                            newAct = activityService.getActivityById(id);
                            if (newAct.getUid() == uid) {
                                newAct.setType(zero);
                            } else {
                                if (Long.parseLong(newAct.getEndTime()) < new Date().getTime()) {
                                    //活动已结束
                                    newAct.setType(four);
                                } else {
                                    Boolean flag = activityService.getActivityByUIdAndAid(uid, id);
                                    if (flag) {
                                        //已报名
                                        newAct.setType(three);
                                    } else {
                                        //未报名
                                        if (newAct.getCost() > 0) {
                                            //收费
                                            newAct.setType(two);
                                        } else {
                                            newAct.setType(one);
                                        }

                                    }
                                }
                            }


                            newAct.setCerateTime(longToDate(Long.parseLong(newAct.getCerateTime())));
                            newAct.setEndTime(longToDate(Long.parseLong(newAct.getEndTime())));
                            newAct.setStartTime(longToDate(Long.parseLong(newAct.getStartTime())));
                            if (newAct.getImages() != null) {
                                newAct.setImageList(Arrays.asList(newAct.getImages().split(",")));
                            }

                            return doObjResp(newAct);
                        } else {
                            return faild("活动不存在~", false);
                        }
                    } else {
                        return faild("参数异常~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
            case 2:
                try {
                    JSONObject jo = JSON.parseObject(json);
                    if (jo != null) {
                        int pageStart = jo.getInteger("pageStart");
                        int pageSize = jo.getInteger("pageSize");
                        int uid = jo.getInteger("uid");

                        List<Activity> activityList = activityService.getPage((pageStart - 1) * pageSize, pageSize);
                        if (activityList != null && activityList.size() > 0) {
                            for (Activity activity : activityList) {

                                if (Long.parseLong(activity.getEndTime()) < new Date().getTime()) {
                                    //活动结束
                                    activity.setType(four);
                                } else {
                                    Boolean flag = activityService.getActivityByUIdAndAid(uid, activity.getId());
                                    if (flag) {
                                        //已报名
                                        activity.setType(three);
                                    } else {
                                        //未报名
                                        if (activity.getCost() > 0) {
                                            //收费
                                            activity.setType(two);
                                        } else {
                                            activity.setType(one);
                                        }

                                    }
                                }

                                if (activity.getStartTime() != null) {
                                    String date = longToDate(Long.parseLong(activity.getStartTime()));
                                    activity.setStartTime(date);
                                }

                                if (activity.getEndTime() != null) {
                                    String date = longToDate(Long.parseLong(activity.getEndTime()));
                                    activity.setEndTime(date);
                                }

                                if (activity.getImages() != null) {
                                    List<String> result = Arrays.asList(activity.getImages().split(","));
                                    activity.setImageList(result);
                                }

                            }
                        }

                        long count = activityService.getPageCount();
                        Page newpage = new Page();
                        newpage.setList(activityList);
                        newpage.setPageSize(pageSize);
                        newpage.setTotalSize(count);
                        return doObjResp(newpage);
                    } else {
                        return faild("参数异常~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }

            case 3:
                try {
                    SignUp signUp = p(json, SignUp.class);
                    if (signUp != null) {

                        if (signUp.getAid() != 0 && signUp.getUid() != 0) {
                            Boolean flag = activityService.getActivityByUIdAndAid(signUp.getUid(), signUp.getAid());
                            if (flag) {
                                return faild("您已报名,不能重复报名", false);
                            }
                            Activity act = activityService.getActivityById(signUp.getAid());
                            if (Long.parseLong(act.getEndTime()) < new Date().getTime()) {
                                //活动结束
                                return faild("活动结束", false);
                            }
                            Member member = memberService.getMemberById(signUp.getUid());

                            //判断是否符合等级
                            if (act != null && member != null) {
                                if (act.getUid() == signUp.getUid()) {
                                    return faild("您是圈主,不能报名", false);
                                }
                                if (member.getLevel().equals(act.getLevel())) {
                                    ////判断人员是否满员,参加人数小于总人数
                                    if (act.getPeopleNumber()==0) {
                                        activityService.signUpRelease(signUp);
                                        return doObjRespSuccess("报名成功");
                                    } else if (act.getJoinNumber() < act.getPeopleNumber()) {
                                        activityService.signUpRelease(signUp);
                                        return doObjRespSuccess("报名成功");
                                    } else {
                                        return faild("名额已满", false);
                                    }
                                } else if (StringUtils.isBlank(act.getLevel())) {
                                    if (act.getJoinNumber() < act.getPeopleNumber()) {
                                        activityService.signUpRelease(signUp);
                                        return doObjRespSuccess("报名成功");
                                    } else {
                                        return faild("名额已满", false);
                                    }
                                } else {
                                    return faild("不符合参与等级要求", false);
                                }


                            } else {
                                return faild("参数为空~", false);
                            }
                        } else {
                            return faild("参数为空~", false);
                        }
                    } else {
                        return faild("参数为空~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }

            case 4:
                try {
                    JSONObject jo = JSON.parseObject(json);
                    if (jo != null) {
                        int pageStart = jo.getInteger("pageStart");
                        int pageSize = jo.getInteger("pageSize");
                        int uid = jo.getInteger("uid");

                        List<Activity> activityList = activityService.getmyReleasePage(uid, (pageStart - 1) * pageSize, pageSize);
                        long releasePagecount = activityService.getmyReleasePageCount(uid);
                        if (activityList != null && activityList.size() > 0) {
                            for (Activity activity : activityList) {

                                if (Long.parseLong(activity.getEndTime()) < new Date().getTime()) {
                                    //活动结束
                                    activity.setType(four);
                                } else {
                                    //收费
                                    activity.setType(two);

                                }

                                if (activity.getStartTime() != null) {
                                    String date = longToDate(Long.parseLong(activity.getStartTime()));
                                    activity.setStartTime(date);
                                }

                                if (activity.getEndTime() != null) {
                                    String date = longToDate(Long.parseLong(activity.getEndTime()));
                                    activity.setEndTime(date);
                                }

                                if (activity.getImages() != null) {
                                    List<String> result = Arrays.asList(activity.getImages().split(","));
                                    activity.setImageList(result);
                                }

                            }
                        }
                        Page newpage = new Page();
                        newpage.setList(activityList);
                        newpage.setPageSize(pageSize);
                        newpage.setTotalSize(releasePagecount);
                        return doObjResp(newpage);
                    } else {
                        return faild("参数异常~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }

            case 5:
                try {
                    JSONObject jo = JSON.parseObject(json);
                    if (jo != null) {
                        int pageStart = jo.getInteger("pageStart");
                        int pageSize = jo.getInteger("pageSize");
                        int uid = jo.getInteger("uid");

                        List<Activity> activityList = activityService.getmyActivityPage(uid, (pageStart - 1) * pageSize, pageSize);
                        long activityPagecount = activityService.getmyActivityCount(uid);
                        if (activityList != null && activityList.size() > 0) {
                            for (Activity activity : activityList) {

                                if (Long.parseLong(activity.getEndTime()) < new Date().getTime()) {
                                    //活动结束
                                    activity.setType(four);
                                } else {
                                    //收费
                                    activity.setType(three);

                                }

                                if (activity.getStartTime() != null) {
                                    String date = longToDate(Long.parseLong(activity.getStartTime()));
                                    activity.setStartTime(date);
                                }

                                if (activity.getEndTime() != null) {
                                    String date = longToDate(Long.parseLong(activity.getEndTime()));
                                    activity.setEndTime(date);
                                }

                                if (activity.getImages() != null) {
                                    List<String> result = Arrays.asList(activity.getImages().split(","));
                                    activity.setImageList(result);
                                }

                            }
                        }
                        Page newpage = new Page();
                        newpage.setList(activityList);
                        newpage.setPageSize(pageSize);
                        newpage.setTotalSize(activityPagecount);
                        return doObjResp(newpage);
                    } else {
                        return faild("参数异常~", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
            case 6:
                SignUpDTO signUpDTO = new SignUpDTO();
                List<SignUpDTO> SignUpDTOList = new ArrayList<>();
                SignUpResp signUpResp = new SignUpResp();
                try {
                    JSONObject jo = JSON.parseObject(json);
                    if (jo != null) {
                        int aid = jo.getInteger("aid");
                        signUpDTO = activityService.getQuanZhuSignUp(aid);
                        SignUpDTOList = activityService.getSignUpInfo(aid);
                        signUpResp.setSignUpDTO(signUpDTO);
                        signUpResp.setSignUpList(SignUpDTOList);
                        return doObjResp(signUpResp);
                    } else {
                        return faild("查无该动态~", false);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
        }
        return null;
    }
}
