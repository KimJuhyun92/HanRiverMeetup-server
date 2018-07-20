package com.hangang.HangangRiver.meeting.dao;

import java.util.Date;
import java.util.List;

import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetailForm;
import org.apache.ibatis.annotations.Param;

public interface MeetingDetailMapper {
	int insert(MeetingDetail meetingDetail);
	MeetingDetail detail(int meeting_seq);
	void update(@Param("meeting_seq") int meeting_seq,
				@Param("meetingDetail") MeetingDetail meetingDetail);
	void delete(int meeting_seq);
	void move(MeetingDetail meetingDetail);
	List<MeetingDetailForm> selectAll(MeetingDetailForm meetingForm);
	boolean isDuplicatedDetail(@Param("meeting_time") Date meeting_time,
							   @Param("user_id") String user_id);
	boolean isExistMeetingDetail(int meeting_seq);
	List<MeetingDetail> selectMyMeetings(String user_id);
}
