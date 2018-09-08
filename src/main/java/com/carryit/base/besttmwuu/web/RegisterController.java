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

	//将手机号，验证码，当前时间，存到短信表
	@RequestMapping(value="/message",method = { RequestMethod.GET, RequestMethod.POST },produces = "application/json;charset=UTF-8")
	public void register(@RequestBody(required = false) String phoneNumber,int code){
		MessageCode phone=messageCodeService.getIdByPhone(phoneNumber);
		if(phone==null){
			MessageCode messageCode=new MessageCode();
			messageCode.setPhone(phoneNumber);
			messageCode.setCode(code);
			messageCode.setCreateTime(new Date());
			messageCode.setStatus(1);

			messageCodeService.insert(messageCode);
		}else{
			MessageCode messageCode=new MessageCode();
			messageCode.setId(phone.getId());
			messageCode.setPhone(phone.getPhone());
			messageCode.setCreateTime(new Date());
			messageCode.setStatus(1);
			messageCodeService.update(messageCode);
		}
	}

	//校验验证码是否正确
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
}
