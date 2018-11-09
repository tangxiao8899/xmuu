package com.carryit.base.besttmwuu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
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
	private final Logger logger = LoggerFactory.getLogger(EasyMobImsServiceImpl.class);
	
	@Autowired
	ImsUsersMapper userMapper;

	@Override
	public List<ImsUsers> getContacts(ImsUsers user) {
		List<String> phones = EasyMobApiUtils.getFriends(user.getPhone(), EasyMobApiUtils.getToken());
		
		logger.info("id为{}的用户当前已有的好友为：{}", user.getUid(), JSON.toJSONString(phones));
		if(phones == null || phones.isEmpty()) {
//			环信中没有任何好友
			logger.info("id为{}的用户当前添加任何好友。", user.getUid());
			return new ArrayList<ImsUsers> ();
		}
		
		//		根据电话查用户
		ImsUsersExample example = new ImsUsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneIn(phones);
		List<ImsUsers> imsUsers = userMapper.selectByExample(example);
		return imsUsers;
	}

}
