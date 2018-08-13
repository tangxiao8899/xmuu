package com.bean;

public class CommonRequest {
	@Override
	public String toString() {
		return "CommonRequest [imei=" + imei + ", idfa=" + idfa + ", token=" + token + ", version=" + version
				+ ", platform=" + platform + ", time=" + time + ", page=" + page + ", userId=" + userId + "]";
	}

	// imei号 android 必须
	public String imei;
	// IOS 必选
	public String idfa;
	// public SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 用户token
	public String token;
	// 接口版本号
	public String version = "10"; // 版本号
	// 平台
	public String platform = "1";// 平台 1-安卓 2-IOS 3-H5
	// 当前时间戳
	public String time = System.currentTimeMillis() + "";

	public int page = 1;

	public int userId = 1;
}
