package com.bean;

public class HttpObjResp<T> extends CommonResp {
	public T data;

	public HttpObjResp() {

	}

	public HttpObjResp(T data) {
		this(data, CODE_SUCCESS, MSG_SUCCESS);
	}

	public HttpObjResp(T data, int code, String msg) {
		this.data = data;
		this.msg = msg;
		this.code = code;
	}

	public HttpObjResp(T data, String msg) {
		this(data, CODE_SUCCESS, msg);
	}

	public T getData() {
		return data == null ? null : data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
