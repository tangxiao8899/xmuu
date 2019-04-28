package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.MerchantProfitStatisticsDao;
import com.carryit.base.xmuu.entity.MerchantProfit;
import com.carryit.base.xmuu.entity.MerchantProfitStatistics;
import com.carryit.base.xmuu.service.MerchantProfitStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class MerchantProfitStatisticsServiceImpl implements MerchantProfitStatisticsService {

    @Autowired
    MerchantProfitStatisticsDao dao;

    @Override
    public MerchantProfit totalMoneyForMonth() {
        return dao.totalMoneyForMonth();
    }

    @Override
    public void statisticsAdMoneyForMonth() {
        MerchantProfit mp = this.totalMoneyForMonth();

        MerchantProfitStatistics entity = new MerchantProfitStatistics();

        entity.setId(UUID.randomUUID().toString().replace("_",""));
        entity.setCity(mp.getCity());
        entity.setProfit(mp.getMerchantProfit());
        entity.setProfitDate(new Date());

        dao.statisticsAdMoneyForMonth(entity);


    }

    @Override
    public Double sameCityMoney(String city) {

        return dao.sameCityMoney(city);
    }
}
