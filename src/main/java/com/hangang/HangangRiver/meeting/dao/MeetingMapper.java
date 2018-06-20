package com.hangang.HangangRiver.meeting.dao;

import com.hangang.HangangRiver.meeting.model.Meeting;

public interface MeetingMapper {
	public void insert(Meeting meeting);
	public Meeting detail(int meeting_seq);
	public void update(Meeting meeting);
	public void delete(int meeting_seq);
	public void move(Meeting meeting);
}
