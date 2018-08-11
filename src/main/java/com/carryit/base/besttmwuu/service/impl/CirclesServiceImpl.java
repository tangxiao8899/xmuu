package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.CirclesDao;
import com.carryit.base.besttmwuu.entity.Circles;
import com.carryit.base.besttmwuu.service.CirclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("circlesService")
public class CirclesServiceImpl implements CirclesService {


    @Autowired
    CirclesDao circlesDao;

    @Override
    public List<Circles> getCircles() {
        return circlesDao.getCircles();
    }
}
