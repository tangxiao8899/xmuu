package com.carryit.base.xmuu.json.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONObjectTest {	

	@Test
	public void test() throws IOException {
		URL url = JSONObjectTest.class.getClassLoader().getResource("friends.json");
		String text = FileUtils.readFileToString(new File(url.getPath()), "utf-8");

	
		JSONObject jsonObject = JSONObject.parseObject(text);
		JSONArray datas = jsonObject.getJSONObject("data").getJSONArray("data");
		List<String> phones = datas.toJavaList(String.class);
		System.out.println(phones);
	}

}
