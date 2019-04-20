package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.UserPrize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPrizeDao {

    int addUserPrize(UserPrize userPrize);

    List<UserPrize> getAllUserPrize();

    UserPrize getUserPrizeById(UserPrize userPrize);

    int deleteUserPrizeById(UserPrize userPrize);

}
