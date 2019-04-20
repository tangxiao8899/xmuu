package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.ImsUserCapitalFlowDao;
import com.carryit.base.xmuu.entity.ImsUserCapitalFlowEntity;
import com.carryit.base.xmuu.service.ImsUserCapitalFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImsUserCapitalFlowServiceImpl implements ImsUserCapitalFlowService {

    @Autowired
    ImsUserCapitalFlowDao imsUserCapitalFlowDao;

    @Override
    public void save(ImsUserCapitalFlowEntity entity) {
        imsUserCapitalFlowDao.save(entity);
    }

    @Override
    public List<ImsUserCapitalFlowEntity> getBillsbyUid(Integer uid) {
        return imsUserCapitalFlowDao.getBillsbyUid(uid);
    }
}
