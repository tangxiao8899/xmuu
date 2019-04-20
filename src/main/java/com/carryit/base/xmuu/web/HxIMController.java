package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.HximService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.base.BaseController.p;

@RestController
@RequestMapping("/hx")
public class HxIMController {

    @Autowired
    HximService hximService;

    /**
     * 获取环信TOKEN
     * @param json
     * @return
     */
    @RequestMapping(value = "/getToken", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo getWxPay(@RequestBody(required = false) String json) {
        try {
            return  hximService.getToken();
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;

        }
    }

    /**
     * 重置IM用户密码
     * @param json
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo resetPassword(@RequestBody(required = false) String json) {
        try {
            return  hximService.resetPassword(json);
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;

        }
    }

    /**
     * 授权注册--单个用户|批量用户
     * @param json
     * @return
     */
    @RequestMapping(value = "/registerUser", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo registerUser(@RequestBody(required = false) String json) {
        try {
            return  hximService.registerUser(json);
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;
        }
    }

    /**
     * 获取--单个用户|批量用户
     * @param json
     * @return
     */
    @RequestMapping(value = "/getUser", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo getUser(@RequestBody(required = false) String json) {
        try {
            return  hximService.getUser(json);
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;
        }
    }

    /**
     * 给 IM 用户添加好友|删除好友
     * @param json
     * @return
     */
    @RequestMapping(value = "/addFriends", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo addFriends(@RequestBody(required = false) String json) {
        try {
            return  hximService.addFriends(json);
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;
        }

    }

    /**
     * 获取 IM 用户的好友列表 | 黑名单
     * @param json
     * @return
     */
    @RequestMapping(value = "/getFriends", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo getFriends(@RequestBody(required = false) String json) {
        try {
            return  hximService.getFriends(json);
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;
        }
    }


    /**
     * 查看用户在线状态
     * @param json
     * @return
     */
    @RequestMapping(value = "/getUserStatus", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo getUserStatus(@RequestBody(required = false) String json) {
        try {
            return  hximService.getUserStatus(json);
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;
        }
    }

    /**
     * 查询离线消息数
     * @param json
     * @return
     */
    @RequestMapping(value = "/offlineMsgCount", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo offlineMsgCount(@RequestBody(required = false) String json) {
        try {
            return  hximService.offlineMsgCount(json);
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;
        }
    }

    /**
     * 发送文本消息
     * @param json
     * @return
     */
    @RequestMapping(value = "/sendMessages", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo sendMessages(@RequestBody(required = false) String json) {
        try {
            return  hximService.sendMessages(json);
        } catch (Exception e) {
            e.printStackTrace();
            ResultPojo jo = new ResultPojo();
            jo.setCode(2);
            jo.setMsg("失败");
            jo.setData("");
            return jo;
        }
    }

}
