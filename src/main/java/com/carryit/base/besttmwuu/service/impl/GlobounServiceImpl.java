package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.GlobounDao;
import com.carryit.base.besttmwuu.entity.Globouns;
import com.carryit.base.besttmwuu.service.GlobounService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("globounService")
public class GlobounServiceImpl implements GlobounService{

    @Autowired
    private GlobounDao globounDao;

    @Override
    public Globouns getFindAll(int uid) {
        return globounDao.getFindAll(uid);
    }
}
