package com.hangang.HangangRiver.meeting.model;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class MeetingDetail {

	private int meeting_seq;
	private int activity_seq;
	private String user_id;
	private String description;
	private int participants_cnt;
	private String meeting_location;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="Asia/Seoul")
	private Date meeting_time;
	private int expected_cost;
	@ApiModelProperty(hidden=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="Asia/Seoul")
	private Date creation_time;
	@ApiModelProperty(hidden=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="Asia/Seoul")
	private Date modification_time;
	private String title;
	private String contact;
	private int contact_seq;
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getContact_seq() {
		return contact_seq;
	}

	public void setContact_seq(int contact_seq) {
		this.contact_seq = contact_seq;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getMeeting_time() {
		return meeting_time;
	}

	public void setMeeting_time(Date meeting_time) {
		this.meeting_time = meeting_time;
	}

	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}

	public Date getModification_time() {
		return modification_time;
	}

	public void setModification_time(Date modification_time) {
		this.modification_time = modification_time;
	}

	public int getMeeting_seq() {
		return meeting_seq;
	}

	public void setMeeting_seq(int meeting_seq) {
		this.meeting_seq = meeting_seq;
	}

	public int getActivity_seq() {
		return activity_seq;
	}

	public void setActivity_seq(int activity_seq) {
		this.activity_seq = activity_seq;
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

	public String getMeeting_location() {
		return meeting_location;
	}

	public void setMeeting_location(String meeting_location) {
		this.meeting_location = meeting_location;
	}

	public int getExpected_cost() {
		return expected_cost;
	}

	public void setExpected_cost(int expected_cost) {
		this.expected_cost = expected_cost;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "MeetingDetail [meeting_seq=" + meeting_seq + ", activity_seq=" + activity_seq + ", user_id=" + user_id
				+ ", description=" + description + ", participants_cnt=" + participants_cnt + ", meeting_location="
				+ meeting_location + ", meeting_time=" + meeting_time + ", expected_cost=" + expected_cost
				+ ", creation_time=" + creation_time + ", modification_time=" + modification_time + ", title=" + title
				+ ", contact=" + contact + "]";
	}

}
