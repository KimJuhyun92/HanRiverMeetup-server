package com.hangang.HangangRiver.timeline.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TimeLine {
	private int timeline_seq;
	private String user_id;
	private String location;
	private String imageurl;
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="Asia/Seoul")
	private Date creation_time;

	public int getTimeline_seq() {
		return timeline_seq;
	}

	public void setTimeline_seq(int timeline_seq) {
		this.timeline_seq = timeline_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}

	@Override
	public String toString() {
		return "TimeLine [timeline_seq=" + timeline_seq + ", user_id=" + user_id + ", location=" + location
				+ ", imageurl=" + imageurl + ", content=" + content + ", creation_time=" + creation_time + "]";
	}

}
