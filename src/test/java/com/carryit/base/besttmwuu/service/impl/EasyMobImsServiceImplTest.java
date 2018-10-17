package com.carryit.base.besttmwuu.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.service.ImsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyMobImsServiceImplTest {
	@Autowired
	ImsService imsService;

	@Test
	public void testGetContacts() {
		ImsUsers user = new ImsUsers();
		user.setPhone("13308699693");
		List<ImsUsers> list = imsService.getContacts(user);
		System.out.println("===================================" + JSON.toJSONString(list));
	}

}
