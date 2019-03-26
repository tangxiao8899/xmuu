package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 环信api工具
 * */
public class EasyMobApiUtils {
	
	/**
	 * @param phone 用户号码
	 * @param token token
	 * @return 该用户的好友电话号码
	 * */
	public static List<String> getFriends(String phone, String token){
//		List<BasicNameValuePair> params = new ArrayList<>();
//		添加参数
//		params.add(new BasicNameValuePair("owner_username", phone));
//		params.add(new BasicNameValuePair("type", "contacts"));
//		params.add(new BasicNameValuePair("token", token));
		Map<String, Object> params = new HashMap<>();
		params.put("owner_username", phone);
		params.put("type", "contacts");
		params.put("token", token);
		
		
		List<String> phones = new ArrayList<>();
		String result = "";
		try {
			result = HttpClientUtil.post("http://47.105.163.51/besttmwuu-0.0.1/hx/getFriends", JSON.toJSONString(params));
		} catch ( IOException e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotBlank(result)) {
			JSONObject jsonObject = JSONObject.parseObject(result);
//			查询成功
			if(jsonObject.getInteger("code").equals(200)) {
				phones = jsonObject.getJSONObject("data").getJSONArray("data").toJavaList(String.class);
			}
		}
		return phones;
	}
	
	
	public static String getToken() {
		String content = "";
		try {
			content = HttpClientUtil.post("http://47.105.163.51/besttmwuu-0.0.1/hx/getToken", "");
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
		if(StringUtils.isNotBlank(content)) {
			JSONObject jsonObject = JSONObject.parseObject(content);
			if(jsonObject.getInteger("code").equals(200)) {
				return jsonObject.getJSONObject("data").getString("access_token");
			}
		}
		return "";
		
	}
}
