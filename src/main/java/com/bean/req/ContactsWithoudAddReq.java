package com.bean.req;

import java.util.List;

import com.bean.CommonRequest;

/**
 * 获取当前用户的所有联系人中，还未添加好友的人员接口参数
 * 格式：
 * {
 * 	"uid": "1000",
 * 	"phones": ["1715486567", "17688889999"]
 * }
 * */
public class ContactsWithoudAddReq extends CommonRequest{
	private int uid;
	private List<String> phones;
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
	
}
