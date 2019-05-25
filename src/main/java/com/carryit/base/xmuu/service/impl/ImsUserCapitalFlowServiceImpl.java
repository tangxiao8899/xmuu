package com.carryit.base.xmuu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carryit.base.xmuu.dao.ImsUserCapitalFlowDao;
import com.carryit.base.xmuu.entity.ImsUserCapitalFlowEntity;
import com.carryit.base.xmuu.service.ImsUserCapitalFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImsUserCapitalFlowServiceImpl implements ImsUserCapitalFlowService {

    @Autowired
    ImsUserCapitalFlowDao imsUserCapitalFlowDao;

    @Override
    public void save(ImsUserCapitalFlowEntity entity) {
        imsUserCapitalFlowDao.save(entity);
    }

    @Override
    public List<ImsUserCapitalFlowEntity> getBillsbyUid(Integer uid) {
        return imsUserCapitalFlowDao.getBillsbyUid(uid);
    }

    @Override
    public Double getMyProfit(Integer uid) {
        return imsUserCapitalFlowDao.getMyProfit(uid);
    }

    @Override
    public JSONObject getMyBill(Integer uid, Integer page, Integer pageSize) {
        JSONObject jo = new JSONObject();

        int index = pageSize * (page -1);
        try {
            List<ImsUserCapitalFlowEntity> list = imsUserCapitalFlowDao.getMyBill(uid,index,pageSize);
            JSONArray ja = new JSONArray();
            for(ImsUserCapitalFlowEntity f : list){
                JSONObject subjo = new JSONObject();
               switch (f.getSource()){
                   case 0:
                       subjo.put("name","充值");
                       break;
                   case 1:
                       subjo.put("name","打赏");
                       break;
                   case 2:
                       subjo.put("name","提现");
                       break;
                   case 3:
                       subjo.put("name","报名支付");
                       break;
                   case 4:
                       subjo.put("name","广告分红");
                   case 5:
                       subjo.put("name","商家分红");
                       break;
                   case 6:
                       subjo.put("name","区域会员升级分红");
                       break;
                   case 7:
                       subjo.put("name","圈子会员升级分红");
                       break;
                   default:
                       break;
               }

               subjo.put("type",f.getType()); // 0：收入  1：支出
               subjo.put("time",f.getCreateTime());
               subjo.put("price",f.getPrice());
               ja.add(subjo);
            }
            jo.put("code",200);
            jo.put("msg","操作成功");
            jo.put("data",ja);
        }catch (Exception e){
            jo.put("code",500);
            jo.put("msg",e.getMessage());
            jo.put("data",null);
        }
        return jo;
    }
}
