package com.carryit.base.xmuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carryit.base.xmuu.service.ImsUserCapitalFlowService;
import com.carryit.base.xmuu.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProfitServiceImpl implements ProfitService {

    @Autowired
    ImsUserCapitalFlowService imsUserCapitalFlowService;

    @Override
    public JSONObject myProfit(String json) {

        JSONObject jo = new JSONObject();

        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            if (!parmJo.containsKey("uid")) { //用户ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            //查询我的分红收益
           double profit =  imsUserCapitalFlowService.getMyProfit(parmJo.getInteger("uid"));
            jo.put("code", 200);
            jo.put("msg", "操作成功");
            jo.put("data", profit);
        }else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
        }

        return jo;
    }

    @Override
    public JSONObject myBill(String json) {
        JSONObject jo = new JSONObject();
        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            if (!parmJo.containsKey("uid")) { //用户ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            //查询我的账单
            jo =  imsUserCapitalFlowService.getMyBill(parmJo.getInteger("uid"),parmJo.getInteger("page"),parmJo.getInteger("pageSize"));

        }else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
        }

        return jo;
    }
}
