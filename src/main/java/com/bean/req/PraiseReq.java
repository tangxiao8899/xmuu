package com.bean.req;

import com.bean.CommonRequest;

//用于接收前端点赞时传递过来的参数
public class PraiseReq extends CommonRequest {
	//动态id
	public int id;
//	点赞用户id
	public int uid;
//	圈子id
	private int bid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	
}
