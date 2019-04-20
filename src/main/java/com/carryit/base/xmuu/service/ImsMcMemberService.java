package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.imsMcMembersWithBLOBs;
import org.springframework.stereotype.Service;

@Service
public interface ImsMcMemberService {
	imsMcMembersWithBLOBs findMemberByUid(int uid);
}
