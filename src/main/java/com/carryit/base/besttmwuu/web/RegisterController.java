package com.carryit.base.besttmwuu.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.carryit.base.besttmwuu.entity.MessageCode;
import com.carryit.base.besttmwuu.service.MessageCodeService;
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
	public MessageCodeService messageCodeService;
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


	//TODO
	//发送短信验证码，调用saveMessageCode(String phoneNum,int code)方法，保存验证码信息

	//TODO
	//校验验证码是否正确，正确->跳转完善个人注册信息页面；错误->给出提示
	@RequestMapping()
	public String check(int code,String phoneNumber) throws ParseException {
		MessageCode messageCode=messageCodeService.getIdByPhone(phoneNumber);
		//时间在有效期内不知道咋写
		if(messageCode.getCode()!=code&&messageCode.getStatus()!=1)
		{
			return "验证码错误";
		}
		Date createDate=messageCode.getCreateTime();
		long date=new Date().getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		long crated=sdf.parse(String.valueOf(createDate)).getTime();
		//判断校验时间<=创建时间+短信有效期
		return null;
	}

	//TODO
	//完善给人信息，调用注册方法 mRegisterService.addUser(User user);  成功->注册完成  失败 ->给出提示

}
