package com.carryit.base.besttmwuu.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.carryit.base.besttmwuu.dao.imsEweiShopMemberMapper;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;
import com.carryit.base.besttmwuu.service.ImsEweiShopMemberService;

@Service
public class ImsEweiShopMemberServiceImpl implements ImsEweiShopMemberService{
	private final Logger log = LoggerFactory.getLogger(ImsEweiShopMemberServiceImpl.class);
	
	@Autowired
	private imsEweiShopMemberMapper memberMapper;
	
	@Override
	public imsEweiShopMember findNicknameavatarAndByUid(int uid) {
		List<imsEweiShopMember> members = memberMapper.findNicknameavatarAndByUid(uid);
		log.info("根据uid({})从ims_ewei_shop_member表中的查询结果为：{}", uid,JSON.toJSONString(members));
		
		if(members.size() > 0) {
			return members.get(0);
		}
		return null;
	}
}
