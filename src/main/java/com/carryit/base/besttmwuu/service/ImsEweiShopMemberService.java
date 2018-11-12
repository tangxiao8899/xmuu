package com.carryit.base.besttmwuu.service;

import org.springframework.stereotype.Service;

import com.carryit.base.besttmwuu.entity.imsEweiShopMember;

@Service
public interface ImsEweiShopMemberService {

	imsEweiShopMember findNicknameavatarAndByUid(int uid);

}
