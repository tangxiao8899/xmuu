package com.carryit.base.xmuu.dao;


import com.carryit.base.xmuu.entity.Prize;
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
