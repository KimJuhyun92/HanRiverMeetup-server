package com.hangang.HangangRiver.meeting.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NotificationLog {
	private int notification_seq;
	private String user_id;
	private String message;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="Asia/Seoul")
	private Date creation_time;
	private boolean isdeleted;

	public int getNotification_seq() {
		return notification_seq;
	}

	public void setNotification_seq(int notification_seq) {
		this.notification_seq = notification_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Override
	public String toString() {
		return "NotificationLog [notification_seq=" + notification_seq + ", user_id=" + user_id + ", message=" + message
				+ ", creation_time=" + creation_time + ", isdeleted=" + isdeleted + "]";
	}

}
