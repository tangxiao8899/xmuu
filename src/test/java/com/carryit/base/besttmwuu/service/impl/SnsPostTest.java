package com.carryit.base.besttmwuu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carryit.base.besttmwuu.BesttmwuuApplication;
import com.carryit.base.besttmwuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.besttmwuu.service.ImsEweiShopSnsPostService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= BesttmwuuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SnsPostTest {
	@Autowired
	ImsEweiShopSnsPostService postService;
	
	@Test
	public void testAdd() {
    	ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs = new ImsEweiShopSnsPostWithBLOBs();
    	imsEweiShopSnsPostWithBLOBs.setPid(5);
    	imsEweiShopSnsPostWithBLOBs.setUid(4);
    	imsEweiShopSnsPostWithBLOBs.setBid(12);
    	imsEweiShopSnsPostWithBLOBs.setContent("测试评论功能");
//    	保存
    	postService.addOne(imsEweiShopSnsPostWithBLOBs);
    	System.out.println("finish");
	}

}
