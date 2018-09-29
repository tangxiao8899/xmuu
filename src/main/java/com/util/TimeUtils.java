package com.util;

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

}
