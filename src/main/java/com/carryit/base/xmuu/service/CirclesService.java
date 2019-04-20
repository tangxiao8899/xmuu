package com.carryit.base.besttmwuu.service;

import com.alibaba.fastjson.JSONObject;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.Circles;
import com.carryit.base.besttmwuu.entity.Culturewall;

import java.util.List;

public interface CirclesService {


    List<Circles> getCircles();

    /**
     * 通过圈子标题模糊匹配
     * @param title 查询条件 圈子标题
     * @return 圈子集合
     */
    JSONObject getCirclesInfo(String json);

    void updateCulturewallByBid(Culturewall culturewall);
}
