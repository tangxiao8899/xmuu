package com.bean;

public class ContactUserResp {
	private Integer uid;

	private String phone;

	private String nickname;
	
	private String avatar;
	
	private Boolean isFriends;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getIsFriends() {
		return isFriends;
	}

	public void setIsFriends(Boolean isFriends) {
		this.isFriends = isFriends;
	}
	
	
	
}
