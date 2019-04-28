package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.MemberUpgradeStatisticsDao;
import com.carryit.base.xmuu.entity.MemberUpgrade;
import com.carryit.base.xmuu.entity.MemberUpgradeStatistics;
import com.carryit.base.xmuu.service.MemberUpgradeStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class MemberUpgradeStatisticsServiceImpl implements MemberUpgradeStatisticsService {

    @Autowired
    MemberUpgradeStatisticsDao dao;

    @Override
    public MemberUpgrade totalMoneyForMonth() {
        return dao.totalMoneyForMonth();
    }

    @Override
    public void statisticsAdMoneyForMonth() {
        MemberUpgrade mu = this.totalMoneyForMonth();
        MemberUpgradeStatistics entity = new MemberUpgradeStatistics();

        entity.setId(UUID.randomUUID().toString().replace("_",""));
        entity.setMoney(mu.getMoney());
        entity.setCircle(mu.getCircle());
        entity.setCity(mu.getCity());
        entity.setStatisticsDate(new Date());

        dao.saveData(entity);

    }

    @Override
    public Double areaUpgradeMoney(String city) {

        return dao.areaUpgradeMoney(city);
    }

    @Override
    public Double areaCircleUpgradeMoney(String city, String circle) {
        return dao.areaCircleUpgradeMoney(city,circle);
    }
}
