package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.CashApplyDao;
import com.carryit.base.besttmwuu.entity.CashApply;
import com.carryit.base.besttmwuu.service.CashApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashApplyServiceImpl implements CashApplyService {

    @Autowired
    CashApplyDao cashApplyDao;

    @Override
    public void save(CashApply cashApply) {
        cashApplyDao.save(cashApply);
    }

    @Override
    public void update(CashApply cashApply) {
        cashApplyDao.update(cashApply);
    }

    @Override
    public int findMaxId() {
        return cashApplyDao.findMaxId();
    }
}
