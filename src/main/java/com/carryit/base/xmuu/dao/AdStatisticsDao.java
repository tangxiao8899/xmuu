package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.AdStatistics;
import org.springframework.stereotype.Repository;

@Repository
public interface AdStatisticsDao {

    Double totalMoneyForMonth();

    void saveData(AdStatistics entity);

    Double adMoneyForMonth();
}
