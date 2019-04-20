package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.entity.imsMcMembersWithBLOBs;
import com.carryit.base.xmuu.service.ImsMcMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImsMcMemberServiceImplTest {
	
	@Autowired
	ImsMcMemberService memberService;
	@Test
	public void testFindMemberByUid() {
		imsMcMembersWithBLOBs membersWithBLOBs = memberService.findMemberByUid(12);
		System.out.println(JSON.toJSONString(membersWithBLOBs));
	}

}
