package com.hangang.HangangRiver.meeting.model;


public class MeetingDetailForm extends MeetingDetail {
	private String startTime;
	private String endTime;

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

	@Override
	public String toString() {
		return "MeetingDetailForm [startTime=" + startTime + ", endTime=" + endTime + "]";
	}


}
