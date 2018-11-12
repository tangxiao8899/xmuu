package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.CashApplyDao;
import com.carryit.base.besttmwuu.entity.CashApply;
import com.carryit.base.besttmwuu.entity.CashDataDTO;
import com.carryit.base.besttmwuu.service.CashApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<CashDataDTO> cashData(String phone,int page,int limit) {
        int size = limit*(page-1);
        return cashApplyDao.cashData(phone,size,limit);
    }

    @Override
    public int count(String phone) {
        return cashApplyDao.count(phone);
    }
}
