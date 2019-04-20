package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.UserPostDao;
import com.carryit.base.xmuu.entity.UserPost;
import com.carryit.base.xmuu.service.UserPostService;
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
