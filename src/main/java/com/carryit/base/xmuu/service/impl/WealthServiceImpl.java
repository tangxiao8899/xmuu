package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.WealthDao;
import com.carryit.base.xmuu.entity.UserDTO;
import com.carryit.base.xmuu.service.WealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wealthService")
public class WealthServiceImpl implements WealthService {

    @Autowired
    private WealthDao wealthDao;
    @Override
    public List<UserDTO> onTheList(long startTime, long endTime) {
        return wealthDao.onTheList(startTime,endTime);
    }


    @Override
    public long queryPageCount(long startTime, long endTime) {
        return wealthDao.queryPageCount(startTime,endTime);
    }

    @Override
    public List<UserDTO> queryPage(long startTime, long endTime) {
        return wealthDao.queryPage(startTime,endTime);
    }
}
