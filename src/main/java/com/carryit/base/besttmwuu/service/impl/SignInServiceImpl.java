package com.carryit.base.besttmwuu.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.carryit.base.besttmwuu.dao.MemberDao;
import com.carryit.base.besttmwuu.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carryit.base.besttmwuu.dao.SincerityDao;
import com.carryit.base.besttmwuu.entity.Sincerity;
import com.carryit.base.besttmwuu.service.SignInService;
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
		Sincerity sincerities = sincerityDao.querySincerityByUserId(uid);

//		过滤出指定日期的签到记录
//		List<Sincerity> sinceritiesBySpecialDate = sincerities.stream()
//				.filter(sincerity -> TimeUtils.getLocalDateTimeFromDate(sincerity.getUpdateTime()).toLocalDate().equals(signDateTime.toLocalDate()))
//				.collect(Collectors.toList());

		if(sincerities!=null) {
			//判断今天有没有签到
			boolean today = TimeUtils.isToday(sincerities.getUpdateTime());
			if(today){
				return false;
			}else {
				sincerities.setUpdateTime(TimeUtils.getDateFromLocalDateTime(signDateTime));
				sincerities.setNumber(sincerities.getNumber() + 1);
				sincerityDao.updateOne(sincerities);

				Member me = memberDao.getWealthById(uid);
				me.setCredit1(me.getCredit1()+10);
				memberDao.updateCredit1(uid,me.getCredit1());
			}

		} else {
			//执行签到
			//构造sincerity对象
			Sincerity newSincerity = new Sincerity();
			newSincerity.setUid(uid);
			newSincerity.setUpdateTime(TimeUtils.getDateFromLocalDateTime(signDateTime));
			newSincerity.setNumber(1F);
			sincerityDao.addOne(newSincerity);

			Member me = memberDao.getWealthById(uid);
			me.setCredit1(me.getCredit1()+10);
			memberDao.updateCredit1(uid,me.getCredit1());
		}
		return true;
	}

}
