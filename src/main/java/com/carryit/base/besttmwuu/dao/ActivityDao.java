package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Activity;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.SignUp;
import com.carryit.base.besttmwuu.entity.SignUpDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao {


    void add(Activity activity);

    long getPageCount();

    List<Activity> getPage(RowBounds rowBounds);

    void signUpRelease(SignUp signUp);

    Activity getActivityById(@Param("aid") Integer aid);

    SignUp getActivityByUIdAndAid(@Param("uid") int uid, @Param("id") Integer id);

    List<Activity> getmyReleasePage(@Param("uid") int uid, RowBounds rowBounds);

    long getmyReleasePageCount(@Param("uid") int uid);

    List<Activity> getmyActivityPage(@Param("uid") int uid, RowBounds rowBounds);

    long getmyActivityCount(@Param("uid") int uid);

    SignUpDTO getQuanZhuSignUp(@Param("aid") int aid);

    List<SignUpDTO> getSignUpInfo(@Param("aid") int aid);
}
