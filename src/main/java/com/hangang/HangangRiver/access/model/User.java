package com.hangang.HangangRiver.access.model;

import java.util.Date;

public class User {
	private String user_id;
	private String nickname;
	private String access_token;
	private String hangang_token;
	private Date last_login_time;
	private Date creation_time;
	private String fcm_token;

	public String getFcm_token() {
		return fcm_token;
	}

	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getHangang_token() {
		return hangang_token;
	}

	public void setHangang_token(String hangang_token) {
		this.hangang_token = hangang_token;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", nickname=" + nickname + ", access_token=" + access_token
				+ ", hangang_token=" + hangang_token + ", last_login_time=" + last_login_time + ", creation_time="
				+ creation_time + "]";
	}

}
