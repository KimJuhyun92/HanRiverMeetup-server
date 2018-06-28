package com.hangang.HangangRiver.meeting.model;


public class MeetingForm extends Meeting {
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
		return "MeetingForm [startTime=" + startTime + ", endTime=" + endTime + "]";
	}


}
