package com.carryit.base.xmuu.service;


import com.carryit.base.xmuu.entity.UserPrize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPrizeService {

    int addUserPrize(UserPrize userPrize);

    List<UserPrize> getAllUserPrize();

    UserPrize getUserPrizeById(UserPrize userPrize);

    int deleteUserPrizeById(UserPrize userPrize);

}
