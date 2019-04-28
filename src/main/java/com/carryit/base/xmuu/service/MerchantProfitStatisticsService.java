package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.MerchantProfit;
import org.apache.ibatis.annotations.Param;

public interface MerchantProfitStatisticsService {

    MerchantProfit totalMoneyForMonth();

    void statisticsAdMoneyForMonth();

    Double sameCityMoney(@Param("city") String city);
}
