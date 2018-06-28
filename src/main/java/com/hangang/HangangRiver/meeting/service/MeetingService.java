package com.hangang.HangangRiver.meeting.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.meeting.dao.MeetingMapper;
import com.hangang.HangangRiver.meeting.model.Meeting;
import com.hangang.HangangRiver.meeting.model.MeetingForm;

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

	public List<MeetingForm> selectTodayMeeting(MeetingForm meetingForm){
		SimpleDateFormat SimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ();
		String time = SimpleDateFormat.format ( currentTime );

		meetingForm.setStartTime(time+" 00:00:00");
		meetingForm.setEndTime(time+" 23:59:59");
		return meetingMapper.selectAll(meetingForm);
	}
}
