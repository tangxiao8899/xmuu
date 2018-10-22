package com.carryit.base.besttmwuu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carryit.base.besttmwuu.dao.imsMcMembersMapper;
import com.carryit.base.besttmwuu.entity.imsMcMembersWithBLOBs;
import com.carryit.base.besttmwuu.service.ImsMcMemberService;

@Service
public class ImsMcMemberServiceImpl implements ImsMcMemberService {

	@Autowired
	imsMcMembersMapper memberMapper;
	

	@Override
	public imsMcMembersWithBLOBs findMemberByUid(int uid) {
		return memberMapper.selectByPrimaryKey(uid);
	}

}
