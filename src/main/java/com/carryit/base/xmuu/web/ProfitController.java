package com.carryit.base.xmuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.carryit.base.xmuu.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收益相关控制
 */
@RestController
@RequestMapping("/profit")
public class ProfitController extends BaseController {

    @Autowired
    ProfitService profitService;

    /**
     * 我的收益
     * @param userId
     * @return
     */
    @RequestMapping(value = "/myProfit", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json;charset=UTF-8")
    public JSONObject myProfit(@RequestBody(required = false) String userId) {
        return callHttpReqTask(userId, 0);
    }

    /**
     * 我的账单
     * @param userId
     * @return
     */
    @RequestMapping(value = "/myBill", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json;charset=UTF-8")
    public JSONObject myBill(@RequestBody(required = false) String userId) {
        return callHttpReqTask(userId, 1);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        JSONObject jo = new JSONObject();
        switch (cmd){
            case 0:
               jo = profitService.myProfit(json);
                break;
            case 1:
              jo =  profitService.myBill(json);
                break;
        }
        return jo;
    }
}
