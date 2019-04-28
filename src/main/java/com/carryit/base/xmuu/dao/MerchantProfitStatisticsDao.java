package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.MerchantProfit;
import com.carryit.base.xmuu.entity.MerchantProfitStatistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProfitStatisticsDao {

    MerchantProfit totalMoneyForMonth();

    void statisticsAdMoneyForMonth(MerchantProfitStatistics entity);

    Double sameCityMoney(@Param("city") String city);
}
