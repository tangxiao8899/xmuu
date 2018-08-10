package com.bean;

import java.util.ArrayList;
import java.util.List;

public class HttpArraysResp<T> extends CommonResp {
	private static final long serialVersionUID = 1L;
	public List<T> data;

	public HttpArraysResp() {
	}

	public HttpArraysResp(List<T> data) {
		this(data, CODE_SUCCESS, MSG_SUCCESS);
	}

	public HttpArraysResp(List<T> data, int code, String msg) {
		this.data = data;
		this.msg = msg;
		this.code = code;
	}

	public HttpArraysResp(List<T> data, String msg) {
		this(data, CODE_SUCCESS, msg);
	}
	
	public List<T> getData() {
		if (data == null) {
			data = new ArrayList<T>();
		}
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}

