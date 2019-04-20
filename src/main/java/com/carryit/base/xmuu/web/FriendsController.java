package com.carryit.base.besttmwuu.web;

import com.base.ResultPojo;
import com.bean.req.FriendsReq;
import com.carryit.base.besttmwuu.service.TFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.base.BaseController.p;

@RestController
@RequestMapping("/friend")
public class FriendsController {

    @Autowired
    private TFriendsService friendsService;

    /**
     * 保存好友申请
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo save(@RequestBody(required = false) String json) {
        try {
            FriendsReq req = p(json, FriendsReq.class);
            return friendsService.save(req);
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
     * 审核好友申请
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo update(@RequestBody(required = false) String json) {
        try {
            FriendsReq req = p(json, FriendsReq.class);
            return friendsService.update(req);
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
     * 获取好友列表
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/getFriends", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo getFriends(@RequestBody(required = false) String json) {
        try {
            return friendsService.getFriends(json);
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
     * 统计好友请求数
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/totalFriends", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResultPojo totalFriends(@RequestBody(required = false) String json) {
        try {
            return friendsService.totalFriends(json);
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
