package com.carryit.base.besttmwuu.service;

import org.springframework.stereotype.Service;

import com.carryit.base.besttmwuu.entity.imsMcMembersWithBLOBs;

@Service
public interface ImsMcMemberService {
	imsMcMembersWithBLOBs findMemberByUid(int uid);
}
