package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.GlobounDao;
import com.carryit.base.xmuu.entity.Globouns;
import com.carryit.base.xmuu.service.GlobounService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("globounService")
public class GlobounServiceImpl implements GlobounService {

    @Autowired
    private GlobounDao globounDao;

    @Override
    public Globouns getFindAll(int uid) {
        return globounDao.getFindAll(uid);
    }
}
