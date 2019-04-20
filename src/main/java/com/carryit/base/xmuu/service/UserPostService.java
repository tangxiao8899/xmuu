package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.UserPost;

public interface UserPostService {


    void saveUserPost(UserPost userPost);

    void updateFabulousByUid(int uid);
}
