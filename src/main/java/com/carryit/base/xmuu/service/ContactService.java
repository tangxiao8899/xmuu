package com.carryit.base.besttmwuu.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.carryit.base.besttmwuu.entity.ImsUsers;

@Service
public interface ContactService {
	
	/**
	 * @param phones 联系电话
	 * @return 未添加的好友
	 * */
	List<ImsUsers> getContactsWithoutAdd(int uid, List<String> phones);
	
	
	/*
	 * @param uid 用户id
	 * @return 好友数量
	 * 查询指定用户的好友数量
	 * 
	 * */
	List<ImsUsers> getFriends(int uid);
}
