package com.hangang.HangangRiver.meeting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.meeting.dao.MeetingMapper;
import com.hangang.HangangRiver.meeting.model.Meeting;

@Service
public class MeetingService {

	@Autowired
	MeetingMapper meetingMapper;

	public void save(Meeting meeting){
		meetingMapper.insert(meeting);
	}

	public Meeting getMeetingDetailById(int meeting_seq){
		return meetingMapper.detail(meeting_seq);
	}

	public void modify(Meeting meeting){
		meetingMapper.update(meeting);
	}

	public void remove(int meeting_seq){
		Meeting meeting = meetingMapper.detail(meeting_seq);
		meetingMapper.move(meeting);
		meetingMapper.delete(meeting_seq);
	}
}
