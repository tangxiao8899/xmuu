package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.UserPost;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostDao {
    void insert(UserPost userPost);

    void updateFabulousByUid(int uid);
}
