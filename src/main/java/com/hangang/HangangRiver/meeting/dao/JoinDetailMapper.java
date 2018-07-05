package com.hangang.HangangRiver.meeting.dao;

import java.util.List;

import com.hangang.HangangRiver.meeting.model.JoinDetail;
public interface JoinDetailMapper {
	public void insert(JoinDetail joinDetail);
	public void delete(int application_seq);
	public List<JoinDetail> getJoinDetails(int meeting_seq);
}
