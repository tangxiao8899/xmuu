package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Prize;

import java.util.List;

public interface PrizeService {

    int addPrize(Prize prize);

    List<Prize> getAllPrize();

    Prize getPrizeById(Prize prize);

    int deletePrizeById(Prize prize);

    int updatePrizeById(Prize prize);
}
