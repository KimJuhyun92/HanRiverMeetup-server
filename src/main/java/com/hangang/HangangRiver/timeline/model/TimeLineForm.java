package com.hangang.HangangRiver.timeline.model;

public class TimeLineForm extends TimeLine {
	private String startTime;
	private String endTime;
	private String nickname;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "MeetingDetailForm [startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
