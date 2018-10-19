package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Activity;
import com.carryit.base.besttmwuu.entity.SignUp;

import java.util.List;

public interface ActivityService {

    void add(Activity activity);

    List<Activity> getPage(int i, int pageSize);

    long getPageCount();


    void signUpRelease(SignUp signUp);

    Activity getActivityById(Integer aid);
}
