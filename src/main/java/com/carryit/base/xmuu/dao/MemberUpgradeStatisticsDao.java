package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.MemberUpgrade;
import com.carryit.base.xmuu.entity.MemberUpgradeStatistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberUpgradeStatisticsDao {

    MemberUpgrade totalMoneyForMonth();

    void saveData(MemberUpgradeStatistics entity);

    Double areaUpgradeMoney(@Param("city") String city);

    Double areaCircleUpgradeMoney(@Param("city") String city, @Param("circle") String circle);

    void saveUpgradeInfo(MemberUpgrade entity);
}
