package com.carryit.base.besttmwuu.service;

import java.util.List;

import com.carryit.base.besttmwuu.entity.ImsUsers;

/**
 * 及时通讯相关服务
 * */
public interface ImsService {
	/**
	 * 查询当前用户的好友用户
	 * @param user 用户
	 * @return 好友用户
	 * */
	List<ImsUsers> getContacts(ImsUsers user);
}
