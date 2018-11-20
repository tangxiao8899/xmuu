package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.UpdateUUDao;
import com.carryit.base.besttmwuu.entity.UpdateUU;
import com.carryit.base.besttmwuu.service.UpdateUUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUUServiceImpl implements UpdateUUService{
    @Autowired
    private UpdateUUDao updateUUDao;

    @Override
    public UpdateUU getFlag() {
        return updateUUDao.getFlag();
    }
}
