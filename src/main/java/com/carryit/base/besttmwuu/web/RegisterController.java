package com.carryit.base.besttmwuu.web;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ServiceConfig;
import com.bean.RegisterReq;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.impl.RegisterServiceImpl;
import com.util.Log;

@RestController
@RequestMapping("/register")
public class RegisterController extends BaseController {
	@SuppressWarnings("unchecked")
	public void resetResultType() {
		this.isHttpArraysJson = false;
	}
	@Autowired
	public RegisterServiceImpl mRegisterService;
	//isHttpArraysJson
	/**
	 * consumes = MediaType.APPLICATION_JSON_VALUE
	 * 
	 * @param data
	 *            = {json}
	 * @return
	 * 伪json
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public JSONObject beginRegister(@RequestParam(value = "data", required = true) String data) {
		Log.e("注册=" + data);
		return callHttpReqTask(data.toString(), 0);
	}

	/**
	 * 纯json
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/reg", method = { RequestMethod.GET, RequestMethod.POST },produces = "application/json;charset=UTF-8")
	public JSONObject beginRegisterII(@RequestBody(required = false) String data) {
		Log.e("注册信息=" + data);
		return callHttpReqTask(data, 0);
	}

	@Override
	public JSONObject runTask(String json, int cmd) {
		switch (cmd) {
		default:
			return register(json);
		}
	}

	public JSONObject register(String json) {
		Log.e("请求字符串=" + json);
		if (json == null || "\"\"".equals(json) || json.isEmpty()) {
			String phone = new Random().nextInt(Integer.MAX_VALUE) + "";
			json = "{\"phone\":\"18311" + phone + "45251\",\"password\":\"123456\"}";
		}
		Log.e("请求字符串长度=" + json.length());
		RegisterReq reqJson = p(json, RegisterReq.class);
		if (reqJson != null) {
			Log.e("reqJson=" + reqJson.toString());
		}
		Log.e("reqParamJson=" + reqParamJson);
		Log.e("debug开关=" + ServiceConfig.DEBUG);
		User data = mRegisterService.selectByPhone(reqJson.phone);
		boolean result = false;
		if (data == null) {
			data = new User();
			data.setUserName(reqJson.phone);
			data.setPassword(reqJson.password);
			result = mRegisterService.addUser(data);
			Log.e("注册结果=" + result);
		} else {
			return faild("该用户已经存在~");
		}
		return result ? doObjResp(data) : faild("注册失败~");
	}
}
