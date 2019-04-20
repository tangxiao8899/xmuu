package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.SincerityDao;
import com.carryit.base.besttmwuu.entity.Sincerity;
import com.carryit.base.besttmwuu.entity.UserDTO;
import com.carryit.base.besttmwuu.service.SincerityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SincerityServiceImpl implements SincerityService {
    @Autowired
    private SincerityDao sincerityDao;


    @Override
    public int getNumberById(int uid) {
        return sincerityDao.getNumberById(uid);
    }

    @Override
    public boolean addLikes(int uid) {
        return sincerityDao.insterLikes(uid);
    }

    @Override
    public void updateCredit(int uid) {
        sincerityDao.updateCredit(uid);
    }

    @Override
    public List<UserDTO> getSincerityList(String startTime, String endTime) {
        return sincerityDao.getSincerityList(startTime,endTime);
    }

    @Override
    public List<UserDTO> queryList(String startTime, String endTime) {
        return sincerityDao.queryList(startTime,endTime);
    }

    @Override
    public void addOne(Sincerity newSincerity) {
        sincerityDao.addOne(newSincerity);
    }
}
