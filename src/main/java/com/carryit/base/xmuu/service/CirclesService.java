package com.carryit.base.xmuu.service;

import com.alibaba.fastjson.JSONObject;
import com.carryit.base.xmuu.entity.Circles;
import com.carryit.base.xmuu.entity.Culturewall;

import java.util.List;

public interface CirclesService {


    List<Circles> getCircles();

    /**
     * 通过圈子标题模糊匹配
     * @param json 查询条件 圈子标题
     * @return 圈子集合
     */
    JSONObject getCirclesInfo(String json);

    void updateCulturewallByBid(Culturewall culturewall);
}
