package com.carryit.base.besttmwuu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carryit.base.besttmwuu.dao.ImsEweiShopSnsPostMapper;
import com.carryit.base.besttmwuu.dao.imsEweiShopMemberMapper;
import com.carryit.base.besttmwuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;
import com.carryit.base.besttmwuu.service.ImsEweiShopSnsPostService;

@Service
public class ImsEweiShopSnsPostServiceImpl implements ImsEweiShopSnsPostService {
	@Autowired
	ImsEweiShopSnsPostMapper postMapper;
	
	@Autowired
	imsEweiShopMemberMapper memberMapper;

	@Override
	public boolean addOne(ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs) {
//		查询评论人的昵称,并设置
		ImsUsers user = new ImsUsers();
		user.setUid(imsEweiShopSnsPostWithBLOBs.getUid());
		imsEweiShopMember member = memberMapper.getByUid(user);
		imsEweiShopSnsPostWithBLOBs.setNickname(member.getNickname());
		
//		设置创建时间,获取时间戳，秒
		long seconds = System.currentTimeMillis()/1000;
		imsEweiShopSnsPostWithBLOBs.setCreatetime(Integer.valueOf(String.valueOf(seconds)));
		
		postMapper.insert(imsEweiShopSnsPostWithBLOBs);
		return true;
	}
	
}
