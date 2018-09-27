package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.Sincerity;
import com.carryit.base.besttmwuu.service.SincerityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikesController extends BaseController {

    @Autowired
    private SincerityService sincerityService;
    Logger logger = LoggerFactory.getLogger(LikesController.class);

    @RequestMapping(value = "/likes", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject likes(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                BoardReq req = p(json, BoardReq.class);
                boolean flag = sincerityService.addLikes(req.uid);

                if (flag) {
                    return doObjRespSuccess("成功");
                } else {
                    return faild("失败~", false);
                }
        }
        return null;
    }
}
