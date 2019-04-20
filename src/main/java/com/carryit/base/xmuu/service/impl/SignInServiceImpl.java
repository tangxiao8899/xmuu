package com.carryit.base.xmuu.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.carryit.base.xmuu.dao.MemberDao;
import com.carryit.base.xmuu.dao.SincerityDao;
import com.carryit.base.xmuu.entity.Member;
import com.carryit.base.xmuu.entity.Sincerity;
import com.carryit.base.xmuu.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.util.TimeUtils;

/**
 * 签到服务类
 * */
@Service("signInService")
public class SignInServiceImpl implements SignInService {
	
	@Autowired
	SincerityDao sincerityDao;
	@Autowired
	MemberDao memberDao;

	@Override
	public boolean sign(Integer uid, LocalDateTime signDateTime) {

//		查询该用户所有签到记录
		List<Sincerity> sincerities = sincerityDao.querySincerityByUserId(uid);
//		过滤出指定日期的签到记录
		List<Sincerity> sinceritiesBySpecialDate = sincerities.stream()
				.filter(sincerity -> TimeUtils.getLocalDateTimeFromDate(sincerity.getCreateTime()).toLocalDate().equals(signDateTime.toLocalDate()))
				.collect(Collectors.toList());
//		如果指定日期没签到，则执行签到
		if(sinceritiesBySpecialDate == null || sinceritiesBySpecialDate.size() == 0) {
			Sincerity newSincerity = new Sincerity();
			newSincerity.setUid(uid);
			newSincerity.setNumber(1);
			sincerityDao.addOne(newSincerity);

			Member me = memberDao.getWealthById(uid);
			me.setCredit1(me.getCredit1()+10);
			memberDao.updateCredit1(uid,me.getCredit1());
			return true;
		} else {
			return false;
		}

	}

}
