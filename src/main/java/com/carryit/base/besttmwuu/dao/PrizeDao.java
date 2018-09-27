package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Prize;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrizeDao {

    int addPrize(Prize prize);

    List<Prize> getAllPrize();

    Prize getPrizeById(Prize prize);

    int deletePrizeById(Prize prize);

    int updatePrizeById(Prize prize);
}
