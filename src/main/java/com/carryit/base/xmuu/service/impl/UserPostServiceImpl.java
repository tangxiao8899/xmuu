package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.UserPostDao;
import com.carryit.base.besttmwuu.entity.UserPost;
import com.carryit.base.besttmwuu.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("uersPostService")
public class UserPostServiceImpl implements UserPostService {
    @Autowired
    UserPostDao userPostDao;

    @Override
    public void saveUserPost(UserPost userPost) {


        userPostDao.insert(userPost);
    }

    @Override
    public void updateFabulousByUid(int uid) {
        userPostDao.updateFabulousByUid(uid);
    }
}
