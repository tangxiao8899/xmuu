package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.UpdateUU;
import com.carryit.base.besttmwuu.service.UpdateUUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update")
public class UpdateController extends BaseController {
    @Autowired
    private UpdateUUService updateUUService;


    //用户中心
    @RequestMapping(value = "/updateUU", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject Member(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 0:
                UpdateUU updateUU = new UpdateUU();
                try {
                    updateUU = updateUUService.getFlag();
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
                return doObjResp(updateUU);
        }
        return null;
    }
}
