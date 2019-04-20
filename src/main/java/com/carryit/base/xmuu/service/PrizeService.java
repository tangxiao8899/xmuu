package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.Prize;

import java.util.List;

public interface PrizeService {

    int addPrize(Prize prize);

    List<Prize> getAllPrize();

    Prize getPrizeById(Prize prize);

    int deletePrizeById(Prize prize);

    int updatePrizeById(Prize prize);
}
