package com.carryit.base.xmuu.service.impl;


import com.carryit.base.xmuu.dao.PrizeDao;
import com.carryit.base.xmuu.entity.Prize;
import com.carryit.base.xmuu.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("prizeService")
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeDao prizeDao;

    @Override
    public int addPrize(Prize prize) {
        return prizeDao.addPrize(prize);
    }

    @Override
    public List<Prize> getAllPrize() {
        return prizeDao.getAllPrize();
    }

    @Override
    public Prize getPrizeById(Prize prize) {
        return prizeDao.getPrizeById(prize);
    }

    @Override
    public int deletePrizeById(Prize prize) {
        return prizeDao.deletePrizeById(prize);
    }

    @Override
    public int updatePrizeById(Prize prize) {
        return prizeDao.updatePrizeById(prize);
    }
}
