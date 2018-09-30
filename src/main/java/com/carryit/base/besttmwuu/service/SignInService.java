package com.carryit.base.besttmwuu.service;

import java.time.LocalDateTime;

// duanyouai
public interface SignInService {
	/**
	 * @param uid 用户id
	 * @param signDateTime 签到日期
	 * @return 返回是否签到成功，每天只允许签到一次，多次签到不成功
	 * */
	boolean sign(Integer uid, LocalDateTime signDateTime);

}
