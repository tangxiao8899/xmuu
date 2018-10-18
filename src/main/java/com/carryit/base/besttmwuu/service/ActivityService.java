package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Activity;

import java.util.List;

public interface ActivityService {

    void add(Activity activity);

    List<Activity> getPage(int i, int pageSize);

    long getPageCount();
}
