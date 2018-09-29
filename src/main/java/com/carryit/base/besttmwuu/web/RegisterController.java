package com.carryit.base.besttmwuu.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.carryit.base.besttmwuu.entity.MessageCode;
import com.carryit.base.besttmwuu.service.MessageCodeService;
import com.carryit.base.besttmwuu.service.UserService;
import com.carryit.base.besttmwuu.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
	@Autowired
	public MessageService messageService;
    @Autowired
    public UserService userService;
	//isHttpArraysJson
	/**
	 * consumes = MediaType.APPLICATION_JSON_VALUE
	 * 
	 * @param data
	 *            = {json}
	 * @return
	 * 伪json
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
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
			case 2:
                User user = p(json, User.class);
                if(user!=null){
                    //获取验证码
                    String code = messageService.securityCode();

                    //保存手机号和验证码
                    messageCodeService.saveMessageCode(user.getPhone(),Integer.parseInt(code));
                    //发送短信
                    Map<String, String> map = messageService.sendSms(user.getPhone(), code);
                    if("1".equals(map.get("code"))){
                        return doObjRespSuccess("成功");
                    }else{
                        return faild("失败~", false);
                    }
                }
			case 3:
				User _user = p(json, User.class);
				if(_user!=null){
					if(_user.getPhone()!=null){
						User data = mRegisterService.selectByPhone(_user.getPhone());
						if(data==null){
							boolean flag = mRegisterService.addUser(_user);
							if(flag){
								return doObjRespSuccess("成功");
							}else{
								return faild("失败~", false);
							}
						}else {
							return faild("用户已存在~", false);
						}

					}else{
						return faild("号码为空~", false);
					}

				}
			case 4:
				return   messageCodeService.checkCode(json);
			case 5:
				JSONObject jo = messageCodeService.checkCode(json);
				if(Integer.valueOf(jo.getString("code")) == 200){
					int i = userService.updatePassWord(json);
					if(i==1){
						return doObjRespSuccess("成功");
					}else {
						return faild("失败~", false);
					}
				}else{
					return faild("失败~", false);
				}
//			default:
//			return register(json);

		}
		return null;
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
			data.setUserName(reqJson.code);
			data.setPassword(reqJson.password);

			data.setAddress(reqJson.address);
			data.setAge(reqJson.age);
			data.setEducation(reqJson.education);
			data.setIdCard(reqJson.idCard);
			data.setIsSingle(reqJson.isSingle);
			data.setNeed(reqJson.need);
			data.setPhone(reqJson.phone);
			data.setSpeciality(reqJson.speciality);
			data.setUserName(reqJson.userName);


			result = mRegisterService.addUser(data);
			Log.e("注册结果=" + result);
		} else {
			return faild("该用户已经存在~");
		}
		return result ? doObjResp(data) : faild("注册失败~");
	}


	//TODO
	//发送短信验证码，调用saveMessageCode(String phoneNum,int code)方法，保存验证码信息
	@RequestMapping(value = "/getSecurityCode")
	public JSONObject getSecurityCode(@RequestBody(required = false) String json) {
		Log.e("号码=" + json);
		return callHttpReqTask(json, 2);
	}
	//TODO
	//校验验证码是否正确，正确->跳转完善个人注册信息页面；错误->给出提示
	@RequestMapping(value = "/checkCode")
	public JSONObject checkCode(@RequestBody(required = false) String json) {
		return callHttpReqTask(json, 4);
	}

	//TODO
	//完善个人信息
	@RequestMapping(value = "/addUser", method = { RequestMethod.GET, RequestMethod.POST })
	public JSONObject addUser(@RequestBody(required = false) String json) {
		Log.e("号码=" + json);
		return callHttpReqTask(json, 3);
	}

	//TODO
	//修改密码
	@RequestMapping(value = "/updatePassWord", method = { RequestMethod.GET, RequestMethod.POST })
	public JSONObject updatePassWord(@RequestBody(required = false) String json) {
		Log.e("号码=" + json);
		return callHttpReqTask(json, 5);
	}
}
