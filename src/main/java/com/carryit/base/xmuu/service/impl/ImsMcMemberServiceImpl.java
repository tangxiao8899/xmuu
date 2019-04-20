package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.imsMcMembersMapper;
import com.carryit.base.xmuu.entity.imsMcMembersWithBLOBs;
import com.carryit.base.xmuu.service.ImsMcMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImsMcMemberServiceImpl implements ImsMcMemberService {

	@Autowired
	imsMcMembersMapper memberMapper;
	

	@Override
	public imsMcMembersWithBLOBs findMemberByUid(int uid) {
		return memberMapper.selectByPrimaryKey(uid);
	}

}
