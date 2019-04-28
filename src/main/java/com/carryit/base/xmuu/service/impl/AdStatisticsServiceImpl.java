package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.AdStatisticsDao;
import com.carryit.base.xmuu.entity.AdStatistics;
import com.carryit.base.xmuu.service.AdStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AdStatisticsServiceImpl implements AdStatisticsService {

    @Autowired
    AdStatisticsDao dao;

    @Override
    public Double totalMoneyForMonth() {
        //统计当月
        return dao.totalMoneyForMonth();
    }

    @Override
    public void statisticsAdMoneyForMonth() {
        Double money = totalMoneyForMonth();
        //写入当前统计数据
        AdStatistics entity = new AdStatistics();
        entity.setId(UUID.randomUUID().toString().replace("-",""));
        entity.setAdStatisticsMoney(money);
        entity.setAdStatisticsDate(new Date());
        dao.saveData(entity);
    }

    @Override
    public Double adMoneyForMonth() {
        return dao.adMoneyForMonth();
    }
}
