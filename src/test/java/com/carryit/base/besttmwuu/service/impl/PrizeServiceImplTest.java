package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.entity.Prize;
import com.carryit.base.besttmwuu.service.PrizeService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrizeServiceImplTest {

    @Autowired
    private PrizeService prizeService;

    private Prize prize = new Prize();

    @Before
    public void init() {
        prize.setName("iphoneXs");
        prize.setNumber(100);
        prize.setPhone("10086");
    }

    @Test
    public void addPrize() {
        prizeService.addPrize(prize);
    }

    @Test
    public void getAllPrize() {
        System.out.println(prizeService.getAllPrize());
    }

    @Test
    public void getPrizeById() {
        Prize prize1 = new Prize();
        prize1.setId(1);
        System.out.println(prizeService.getPrizeById(prize1));
    }

    @Test
    public void deletePrizeById() {
        Prize prize1 = new Prize();
        prize1.setId(1);
        System.out.println(prizeService.deletePrizeById(prize1));
    }

    @Test
    public void updatePrizeById() {
        Prize prize1 = new Prize();
        prize1.setId(1);
        prize1.setNumber(1000);
        System.out.println(prizeService.deletePrizeById(prize1));
    }
}