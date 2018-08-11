package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.WealthDao;
import com.carryit.base.besttmwuu.entity.UserDTO;
import com.carryit.base.besttmwuu.service.WealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wealthService")
public class WealthServiceImpl implements WealthService {

    @Autowired
    private WealthDao wealthDao;
    @Override
    public List<UserDTO> onTheList(String startTime, String endTime) {
        return wealthDao.onTheList(startTime,endTime);
    }
}
