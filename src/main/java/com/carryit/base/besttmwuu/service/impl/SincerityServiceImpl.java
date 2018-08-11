package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.SincerityDao;
import com.carryit.base.besttmwuu.entity.Sincerity;
import com.carryit.base.besttmwuu.service.SincerityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SincerityServiceImpl implements SincerityService {
    @Autowired
    private SincerityDao sincerityDao;


    @Override
    public Sincerity getNumberById(int uid) {
        return sincerityDao.getNumberById(uid);
    }
}
