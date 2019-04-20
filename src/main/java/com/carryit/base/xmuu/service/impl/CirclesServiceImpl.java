package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.Page;
import com.carryit.base.besttmwuu.dao.CirclesDao;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.Circles;
import com.carryit.base.besttmwuu.entity.Culturewall;
import com.carryit.base.besttmwuu.service.CirclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service("circlesService")
public class CirclesServiceImpl implements CirclesService {


    @Autowired
    CirclesDao circlesDao;

    @Override
    public List<Circles> getCircles() {
        return circlesDao.getCircles();
    }

    @Override
    public JSONObject getCirclesInfo(String json) {
        JSONObject jo = new JSONObject();
        if(StringUtils.isEmpty(json)){
            jo.put("msg","请求参数不能为空");
            jo.put("code",400);
            jo.put("data",null);
        }else{
            JSONObject subJo = JSON.parseObject(json);
            String title = subJo.getString("title");
            int pageSize = Integer.valueOf(subJo.getString("pageSize")); //每页数量
            int pageIndex = Integer.valueOf(subJo.getString("pageIndex")); //当前页

            int index = pageSize*(pageIndex-1);
            List<Circles> list = circlesDao.getCirclesInfo(title,index,pageSize);
            jo.put("msg","请求成功");
            jo.put("code",200);
            jo.put("data",list == null ? "":list);

        }

        return jo;
    }

    @Override
    public void updateCulturewallByBid(Culturewall culturewall) {
        circlesDao.updateCulturewallByBid(culturewall);
    }


}
