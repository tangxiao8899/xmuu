package com.bean.req;

import com.aliyuncs.CommonRequest;

//接收评论参数接口
public class CommentReq extends CommonRequest {
	private Integer pid;
	private Integer uid;
	private String content;
	private Integer bid;
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	
	
}
