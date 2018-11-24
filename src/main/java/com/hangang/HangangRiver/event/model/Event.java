package com.hangang.HangangRiver.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Event {
	private int event_seq;
	private String imageurl;
	private boolean isongoing;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="Asia/Seoul")
	private Date creation_time;
	private String url;

	public int getEvent_seq() {
		return event_seq;
	}

	public void setEvent_seq(int event_seq) {
		this.event_seq = event_seq;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setUrl(String url) { this.url = url; }

	public String getUrl() { return url; }

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public boolean isIsongoing() {
		return isongoing;
	}

	public void setIsongoing(boolean isongoing) {
		this.isongoing = isongoing;
	}

	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}

	@Override
	public String toString() {
		return "Event [event_seq=" + event_seq + ", imageurl=" + imageurl + ", isongoing=" + isongoing
				+ ", creation_time=" + creation_time + "]";
	}

}
