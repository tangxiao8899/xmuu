package com.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.CommonRequest;
import com.bean.CommonResp;
import com.bean.Domain;
import com.bean.HttpArraysResp;
import com.bean.HttpObjResp;
import com.util.Log;

public abstract class BaseController<Req extends CommonRequest, Resp extends Domain> {
	public BaseController() {
		resetResultType(); // 默认是数组
	}

	public Req reqParamJson;
	@Autowired
	protected HttpServletRequest request;
	protected CommonRequest commonReq;
	public boolean isHttpArraysJson = true; // 默认认为请求的返回data为数组

	public void resetResultType() {
		this.isHttpArraysJson = true;
	}

	public static final String REQUEST_TYPE = "GET";

	public abstract JSONObject runTask(String json, int cmd);

	public boolean check(String json, boolean skip) {
		System.out.println("=校验逻辑=");
		//commonReq = (CommonRequest) JSON.parseObject(json, CommonRequest.class);
		return true;
	}

	public JSONObject callHttpReqTask(String json, int cmd) {
		return callHttpReqTask(json, false, cmd);
	}

	/**
	 * 
	 * @param json
	 * @param skip
	 *            跳过参数检测<br/>
	 * @param cmd
	 * @return
	 */
	public JSONObject callHttpReqTask(String json, boolean skip, int cmd) {
		Log.e("#请求方式#" + request.getMethod());
		if (!ServiceConfig.DEBUG) { // 正式版本,get请求不支持
			if (request.getMethod().equalsIgnoreCase(REQUEST_TYPE)) {
				return faild("不支持get请求~");
			}
		}
		Log.e("#debug=[" + ServiceConfig.DEBUG + "]模式,get方式放行#" + request.getMethod());
		JSONObject result;
		if (check(json, skip)) {
			result = runTask(json, cmd);
		} else {
			result = faild();
		}
		return result;
	}

	public JSONObject faild() {
		return faild(CommonResp.MSG_FAIL);
	}

	public JSONObject faild(String msg) {
		return faild(CommonResp.CODE_FAIL, msg);
	}

	public JSONObject faild(int code, String msg) {
		return faild(code, msg, isHttpArraysJson);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject faild(String msg, boolean isHttpArraysJson) {
		JSONObject result;
		if (isHttpArraysJson) {
			result = BaseController.doArraysResp(new ArrayList(), CommonResp.CODE_FAIL, msg);
		} else {
			result = BaseController.doObjResp(new Object(), CommonResp.CODE_FAIL, msg);
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject faild(int code, String msg, boolean isHttpArraysJson) {
		JSONObject result;
		if (isHttpArraysJson) {
			result = BaseController.doArraysResp(new ArrayList(), code, msg);
		} else {
			result = BaseController.doObjResp(new Object(), code, msg);
		}
		return result;
	}

	public static <T> T p(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	public static <T> JSONObject doArraysResp(List<T> resp) {
		return doArraysResp(resp, CommonResp.MSG_SUCCESS);
	}

	public static <T> JSONObject doArraysResp(List<T> resp, String msg) {
		return doArraysResp(resp, CommonResp.CODE_SUCCESS, msg);
	}

	/**
	 * ===格式=== { "msg": "ok", " code": 1, "data":[] } ===格式===
	 */
	public static <T> JSONObject doArraysResp(List<T> resp, int code, String msg) {
		return (JSONObject) JSON.toJSON(new HttpArraysResp<T>(resp, code, msg));
	}

	public static <T> JSONObject doObjResp(T resp) {
		return doObjResp(resp, CommonResp.MSG_SUCCESS);
	}

	public static <T> JSONObject doObjResp(T resp, String msg) {
		return doObjResp(resp, CommonResp.CODE_SUCCESS, msg);
	}

	/**
	 * 只提示成功|失败
	 * 
	 * @param msg
	 * @return
	 */
	public static <T> JSONObject doObjRespSuccess(String msg) {
		return doObjResp(null, CommonResp.CODE_SUCCESS, msg);
	}

	/**
	 * 只提示成功|失败
	 * 
	 * @param msg
	 * @return
	 */
	public static <T> JSONObject doArraysRespSuccess(String msg) {
		return doArraysResp(null, CommonResp.CODE_SUCCESS, msg);
	}

	/**
	 * ===格式=== { "msg": "ok", " code": 1, "data":{} } ===格式===
	 */
	public static <T> JSONObject doObjResp(T resp, int code, String msg) {
		return (JSONObject) JSON.toJSON(new HttpObjResp<T>(resp, code, msg));
	}
}
