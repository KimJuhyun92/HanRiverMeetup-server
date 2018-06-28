package com.hangang.HangangRiver.meeting.dao;

import java.util.List;

import com.hangang.HangangRiver.meeting.model.Meeting;
import com.hangang.HangangRiver.meeting.model.MeetingForm;

public interface MeetingMapper {
	public void insert(Meeting meeting);
	public Meeting detail(int meeting_seq);
	public void update(Meeting meeting);
	public void delete(int meeting_seq);
	public void move(Meeting meeting);
	public List<MeetingForm> selectAll(MeetingForm meetingForm);
}
