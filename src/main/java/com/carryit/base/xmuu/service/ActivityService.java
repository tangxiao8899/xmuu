package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Activity;
import com.carryit.base.besttmwuu.entity.SignUp;
import com.carryit.base.besttmwuu.entity.SignUpDTO;

import java.util.List;

public interface ActivityService {

    void add(Activity activity);

    List<Activity> getPage(int i, int pageSize);

    long getPageCount();


    void signUpRelease(SignUp signUp);

    Activity getActivityById(Integer aid);

    Boolean getActivityByUIdAndAid(int uid, Integer id);

    List<Activity> getmyReleasePage(int uid, int i, int pageSize);

    long getmyReleasePageCount(int uid);

    List<Activity> getmyActivityPage(int uid, int i, int pageSize);

    long getmyActivityCount(int uid);

    SignUpDTO getQuanZhuSignUp(int aid);

    List<SignUpDTO> getSignUpInfo(int aid);
}
