package com.hangang.HangangRiver.meeting.dao;

import java.util.List;

import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;

import org.apache.ibatis.annotations.Param;

public interface JoinDetailMapper {
	void insert(JoinDetail joinDetail);
	void delete(int application_seq);
	JoinDetail getJoinDetail(int application_seq);
	List<JoinDetail> getJoinDetails(int meeting_seq);
	List<JoinDetail> getJoinDetailsByUserId(String user_id);
	boolean isExistJoinDetails(@Param("meeting_seq") int meeting_seq,
							   @Param("user_id") String user_id);
	List<MeetingDetail> selectMyApplications(String user_id);
	int getJoinDetailCount(int meeting_seq);
}
