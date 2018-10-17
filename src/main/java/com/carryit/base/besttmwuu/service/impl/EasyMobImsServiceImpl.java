package com.carryit.base.besttmwuu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carryit.base.besttmwuu.dao.ImsUsersMapper;
import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.entity.ImsUsersExample;
import com.carryit.base.besttmwuu.entity.ImsUsersExample.Criteria;
import com.carryit.base.besttmwuu.service.ImsService;
import com.util.EasyMobApiUtils;

/**
 * 环信即时通讯相关服务
 * */
@Service
public class EasyMobImsServiceImpl implements ImsService {
	
	@Autowired
	ImsUsersMapper userMapper;

	@Override
	public List<ImsUsers> getContacts(ImsUsers user) {
		List<String> phones = EasyMobApiUtils.getFriends(user.getPhone(), EasyMobApiUtils.getToken());
		//		根据电话查用户
		ImsUsersExample example = new ImsUsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneIn(phones);
		List<ImsUsers> imsUsers = userMapper.selectByExample(example);
		return imsUsers;
	}

}
