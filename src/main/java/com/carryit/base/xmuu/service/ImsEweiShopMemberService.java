package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.imsEweiShopMember;
import org.springframework.stereotype.Service;

@Service
public interface ImsEweiShopMemberService {

	imsEweiShopMember findNicknameavatarAndByUid(int uid);

}
