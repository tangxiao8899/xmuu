package com.carryit.base.besttmwuu.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public boolean sign(Integer uid, LocalDateTime signDateTime) {
//		构造sincerity对象
		Sincerity newSincerity = new Sincerity();
		newSincerity.setUid(uid);
		newSincerity.setUpdateTime(TimeUtils.getDateFromLocalDateTime(signDateTime));
		newSincerity.setNumber(1F);
		
		
//		查询该用户所有签到记录
		List<Sincerity> sincerities = sincerityDao.querySincerityByUserId(newSincerity);
//		过滤出指定日期的签到记录
		List<Sincerity> sinceritiesBySpecialDate = sincerities.stream()
				.filter(sincerity -> TimeUtils.getLocalDateTimeFromDate(sincerity.getUpdateTime()).toLocalDate().equals(signDateTime.toLocalDate()))
				.collect(Collectors.toList());
//		如果指定日期没签到，则执行签到，否则更新时间，并且签到次数+1
		if(sinceritiesBySpecialDate == null || sinceritiesBySpecialDate.size() > 0) {
			Sincerity sincerity = sinceritiesBySpecialDate.get(0);
			sincerity.setUpdateTime(TimeUtils.getDateFromLocalDateTime(signDateTime));
			sincerity.setNumber(sincerity.getNumber() + 1);
			sincerityDao.updateOne(sincerity);
		} else {
//			执行签到
			sincerityDao.addOne(newSincerity);
		}
		return true;
	}

}
