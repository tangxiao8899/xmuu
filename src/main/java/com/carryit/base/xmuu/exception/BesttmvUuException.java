package com.carryit.base.besttmwuu.exception;

import java.io.Serializable;

/**
 * 定义异常
 * */
public class BesttmvUuException extends Exception implements Serializable {

	private static final long serialVersionUID = 4214881718933113256L;

	public BesttmvUuException() {
		super();
	}

	public BesttmvUuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BesttmvUuException(String message, Throwable cause) {
		super(message, cause);
	}

	public BesttmvUuException(String message) {
		super(message);
	}

	public BesttmvUuException(Throwable cause) {
		super(cause);
	}
	
	

}
