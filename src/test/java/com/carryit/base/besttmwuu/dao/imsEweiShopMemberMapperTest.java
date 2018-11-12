package com.carryit.base.besttmwuu.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;

@RunWith(SpringRunner.class)
@SpringBootTest
public class imsEweiShopMemberMapperTest {
	@Autowired
	private imsEweiShopMemberMapper memberMapper;

	@Test
	public void testFindNicknameavatarAndByUidInt() {
		List<imsEweiShopMember> members = memberMapper.findNicknameavatarAndByUid(2222222);
		System.out.println(members.size());
		System.out.println(JSON.toJSONString(members));
	}

}
