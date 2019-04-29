package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.MemberUpgrade;
import org.apache.ibatis.annotations.Param;

public interface MemberUpgradeStatisticsService {

    MemberUpgrade totalMoneyForMonth();

    void statisticsAdMoneyForMonth();

    /**
     * 该区域升级总额
     * @param city
     * @return
     */
    Double areaUpgradeMoney(@Param("city") String city);


    /**
     * 该区域该圈子升级总额
     * @param city
     * @param circle
     * @return
     */
    Double areaCircleUpgradeMoney(@Param("city") String city, @Param("circle") String circle);


    void saveUpgradeInfo(MemberUpgrade entity);

}
