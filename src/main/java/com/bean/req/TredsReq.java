package com.bean.req;

import com.aliyuncs.CommonRequest;

//接收动态参数接口
public class TredsReq extends CommonRequest {
	private Integer uid;
	private String content;
	private String images;
	private Integer bid;

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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
