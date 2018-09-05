package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.HximService;
import com.util.JerseyClientUtil;
import com.util.PropertyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HximServiceImpl implements HximService {

    @Override
    public ResultPojo getToken() throws Exception{


        JSONObject param = new JSONObject();
        param.put("grant_type","client_credentials");
        param.put("client_id",PropertyUtil.getProperty("hxim.clientId"));
        param.put("client_secret",PropertyUtil.getProperty("hxim.clientSecret"));

        return JerseyClientUtil.postMethod(param.toString(),"/token");

    }

    @Override
    public ResultPojo registerUser(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if(!StringUtils.isEmpty(json)){
            JSONObject jo = JSON.parseObject(json);
            //校验授权信息
            if(!jo.containsKey("token")){
                rp.setStatus(401);
                rp.setErrorMsg("请先获取授权信息");
            }
            //判断是单个注册还是多个注册
            if(jo.containsKey("users")){ // 多个注册
                JSONArray ja = JSONArray.parseArray(jo.getString("users"));
                //校验参数
                for(int i =0;i<ja.size();i++){
                    JSONObject jao = ja.getJSONObject(i);
                    if (checkParam(rp, jao)) return rp;
                }
                rp =  JerseyClientUtil.postMethod(ja.toString(),jo.getString("token"),"/users");
            }else{ // 单个注册
                //校验参数
                if (checkParam(rp, jo)) return rp;

                JSONObject subJo = new JSONObject();
                subJo.put("username",jo.getString("username"));
                subJo.put("password",jo.getString("password"));
                rp = JerseyClientUtil.postMethod(subJo.toString(),jo.getString("token"),"/users");

            }
        }else{
            rp.setStatus(400);
            rp.setErrorMsg("请求参数异常");

        }
        return rp;

    }

    /**
     * 校验参数
     * @param rp
     * @param jao
     * @return
     */
    private boolean checkParam(ResultPojo rp, JSONObject jao) {
        if(!jao.containsKey("username") || !jao.containsKey("password")){
            rp.setStatus(400);
            rp.setErrorMsg("请求参数异常");
            return true;
        }
        return false;
    }


}
