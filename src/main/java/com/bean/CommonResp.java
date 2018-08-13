package com.bean;

import java.io.Serializable;

public class CommonResp implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String OK = "ok";

	public static final int CODE_SUCCESS = 200;

	public static final int CODE_FAIL = 2;

	public static final int CODE_SYS_ERR = 3;

	public static final String MSG_SUCCESS = "成功";

	public static final String MSG_FAIL = "失败";

	public static final String MSG_SYS_ERR = "系统错误";

	public int code;
	public String msg;
	// public String time = DateUtil.getCurrDateString();
	// public int page = 1;

//	public boolean isSuccess() {
//		return code == CODE_SUCCESS; // 200 成功
//	}
}
