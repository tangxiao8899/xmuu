package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.Page;
import com.carryit.base.besttmwuu.dao.CirclesDao;
import com.carryit.base.besttmwuu.entity.Circles;
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
            jo.put("data","");
        }else{
            JSONObject subJo = JSON.parseObject(json);
            String title = subJo.getString("title");
            int pageSize = Integer.valueOf(subJo.getString("pageSize")); //每页数量
            int pageIndex = Integer.valueOf(subJo.getString("pageIndex")); //当前页

            int index = pageIndex >1 ? pageSize * (pageIndex-1) +1 : pageIndex;
            int size = index == 0 ? pageSize: pageIndex * pageSize;

            List<Circles> list = circlesDao.getCirclesInfo(title,index,size);
            jo.put("msg","请求成功");
            jo.put("code",200);
            jo.put("data",list == null ? "":list);

        }

        return jo;
    }


}
