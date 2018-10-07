package com.bean.req;
import java.io.Serializable;

//接收动态参数接口
public class TredsReq implements Serializable {
	private Integer uid;  //用户id
	private String content; //文字内容
	private String image;  //图片路径，逗号拼接  images1，images2，images3，images4
	private Integer bid;  //圈子id

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}
}
