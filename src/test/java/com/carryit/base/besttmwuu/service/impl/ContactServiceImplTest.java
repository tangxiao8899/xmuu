package com.carryit.base.besttmwuu.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.service.ContactService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceImplTest {
	@Autowired
	ContactService contactService;

	@Test
	public void testGetContactsWithoutAdd() {
		List<String> phones = Arrays.asList("17762369527","18772374640","13308699693","13571860966","18171277713","18727582389","15171635573", "1122", "3344");
		List<ImsUsers> notFrineds = contactService.getContactsWithoutAdd(21, phones);
		System.out.println(JSON.toJSON(notFrineds));
	}

}
