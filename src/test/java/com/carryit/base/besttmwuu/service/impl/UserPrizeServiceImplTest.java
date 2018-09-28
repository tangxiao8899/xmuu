package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.entity.UserPrize;
import com.carryit.base.besttmwuu.service.UserPrizeService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPrizeServiceImplTest {
    @Autowired
    private UserPrizeService userPrizeService;
    private UserPrize userPrize = new UserPrize();

    @Before
    public void init(){
        userPrize.setPid(12);
        userPrize.setUid("hlzou");
    }

    @Test
    public void addUserPrize() {
        userPrizeService.addUserPrize(userPrize);
    }

    @Test
    public void getAllUserPrize() {
        userPrizeService.getAllUserPrize();
    }

    @Test
    public void getUserPrizeById() {
        UserPrize userPrize1 = new UserPrize();
        userPrize1.setId(1);
        userPrizeService.getUserPrizeById(userPrize1);
    }

    @Test
    public void deleteUserPrizeById() {
        UserPrize userPrize1 = new UserPrize();
        userPrize1.setId(1);
        userPrizeService.deleteUserPrizeById(userPrize1);
    }
}