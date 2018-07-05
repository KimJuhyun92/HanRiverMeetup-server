package com.hangang.HangangRiver.meeting.model;

import java.util.Date;

public class JoinDetail {
	private int application_seq;
	private int meeting_seq;
	private String user_id;
	private String description;
	private int participants_cnt;
	private Date application_time;
	private String contact;

	public int getApplication_seq() {
		return application_seq;
	}

	public void setApplication_seq(int application_seq) {
		this.application_seq = application_seq;
	}

	public int getMeeting_seq() {
		return meeting_seq;
	}

	public void setMeeting_seq(int meeting_seq) {
		this.meeting_seq = meeting_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParticipants_cnt() {
		return participants_cnt;
	}

	public void setParticipants_cnt(int participants_cnt) {
		this.participants_cnt = participants_cnt;
	}

	public Date getApplication_time() {
		return application_time;
	}

	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "JoinDetail [application_seq=" + application_seq + ", meeting_seq=" + meeting_seq + ", user_id="
				+ user_id + ", description=" + description + ", participants_cnt=" + participants_cnt
				+ ", application_time=" + application_time + ", contact=" + contact + "]";
	}

}
