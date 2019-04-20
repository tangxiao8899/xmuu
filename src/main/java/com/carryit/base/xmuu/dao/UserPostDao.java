package com.carryit.base.besttmwuu.dao;


import com.carryit.base.besttmwuu.entity.UserPost;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostDao {
    void insert(UserPost userPost);

    void updateFabulousByUid(int uid);
}
