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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
