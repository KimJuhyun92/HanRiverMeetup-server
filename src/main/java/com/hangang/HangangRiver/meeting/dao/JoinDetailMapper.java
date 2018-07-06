package com.hangang.HangangRiver.meeting.dao;

import java.util.List;

import com.hangang.HangangRiver.meeting.model.JoinDetail;
import org.apache.ibatis.annotations.Param;

public interface JoinDetailMapper {
	public void insert(JoinDetail joinDetail);
	public void delete(int application_seq);
	public List<JoinDetail> getJoinDetails(int meeting_seq);
	boolean isExistJoinDetails(@Param("meeting_seq") int meeting_seq,
							   @Param("user_id") String user_id);
}
