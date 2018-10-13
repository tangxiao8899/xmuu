package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.UserLevel;
import com.carryit.base.besttmwuu.service.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/level")
public class UserLevelController extends BaseController {

    @Autowired
    private UserLevelService userLevelService;




    //用户等级商品
    @RequestMapping(value = "/level", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject Level(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }



    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd){
            case 0:
                UserLevel lv=new UserLevel();
                try {
                    BoardReq req= p(json, BoardReq.class);
                    if (req!=null){
                        lv=userLevelService.getLevel();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }


        }


        return null;
    }
}
