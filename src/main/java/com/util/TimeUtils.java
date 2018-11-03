package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

// duanyouai
public class TimeUtils {
	
	public static LocalDateTime getLocalDateTimeFromDate(Date date) {
	    Instant instant = date.toInstant();
	    ZoneId zoneId = ZoneId.systemDefault();
	    return instant.atZone(zoneId).toLocalDateTime();
	}
	
	public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();        
        ZonedDateTime zdt = localDateTime.atZone(zoneId);        
        return Date.from(zdt.toInstant());
	}


	public static boolean isToday(Date inputJudgeDate) {
		boolean flag = false;
		//获取当前系统时间
		long longDate = System.currentTimeMillis();
		Date nowDate = new Date(longDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(nowDate);
		String subDate = format.substring(0, 10);
		//定义每天的24h时间范围
		String beginTime = subDate + " 00:00:00";
		String endTime = subDate + " 23:59:59";
		Date paseBeginTime = null;
		Date paseEndTime = null;
		try {
			paseBeginTime = dateFormat.parse(beginTime);
			paseEndTime = dateFormat.parse(endTime);
		} catch (ParseException e) {
		}
		if(inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
			flag = true;
		}
		return flag;
	}

}
