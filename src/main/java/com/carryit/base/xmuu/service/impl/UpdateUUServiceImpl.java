package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.UpdateUUDao;
import com.carryit.base.xmuu.entity.UpdateUU;
import com.carryit.base.xmuu.service.UpdateUUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUUServiceImpl implements UpdateUUService {
    @Autowired
    private UpdateUUDao updateUUDao;

    @Override
    public UpdateUU getFlag() {
        return updateUUDao.getFlag();
    }
}
