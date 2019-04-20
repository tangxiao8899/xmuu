package com.carryit.base.besttmwuu.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.carryit.base.besttmwuu.dao.ImsUsersMapper;
import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.entity.ImsUsersExample;
import com.carryit.base.besttmwuu.entity.ImsUsersExample.Criteria;
import com.carryit.base.besttmwuu.service.ContactService;
import com.carryit.base.besttmwuu.service.ImsService;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	ImsUsersMapper userMapper;
	
	@Autowired
	ImsService imsService;

	@Override
	public List<ImsUsers> getContactsWithoutAdd(int uid, List<String> phones) {
		ImsUsers user = userMapper.selectByPrimaryKey(uid);
//		1. 从环信提供的api查询已添加的好友：
		List<ImsUsers> friends = imsService.getContacts(user);
		
//		2. 待添加好友=所有联系人-已添加好友
		Set<String> friendPhones = friends.stream().map(u -> u.getPhone()).collect(Collectors.toSet());
		Set<String> allPhones = new HashSet<>(phones);
		allPhones.removeAll(friendPhones);
		System.out.println("未添加的好友：" + JSON.toJSONString(allPhones));
//		HashSet<String> hashSet = new HashSet<>(phones);
		
//		3、 根据前端传递过来的电话，查询所有对应的用户（排除当前用户）
		ImsUsersExample example = new ImsUsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneIn(new ArrayList<String>(allPhones));
		criteria.andUidNotEqualTo(uid);
		List<ImsUsers> imsUsers = userMapper.selectByExample(example);
	
		return imsUsers;
	}

	@Override
	public List<ImsUsers> getFriends(int uid) {
		ImsUsers user = userMapper.selectByPrimaryKey(uid);
//		未查询到用户时：
		if(user == null) {
			return new ArrayList<>();
		}
		return imsService.getContacts(user);
	}


}
