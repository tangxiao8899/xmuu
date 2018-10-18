package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.ActivityDao;
import com.carryit.base.besttmwuu.dao.BoardDao;
import com.carryit.base.besttmwuu.entity.Activity;
import com.carryit.base.besttmwuu.service.ActivityService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("activityServiceImpl")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;
    @Override
    public void add(Activity activity) {
        activityDao.add(activity);
    }

    @Override
    public List<Activity> getPage(int pageStart, int pageSize) {
        return activityDao.getPage(new RowBounds(pageStart,pageSize));
    }

    @Override
    public long getPageCount() {
        return activityDao.getPageCount();
    }
}
