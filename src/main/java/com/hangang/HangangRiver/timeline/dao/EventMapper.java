package com.hangang.HangangRiver.timeline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.timeline.model.Event;

public interface EventMapper {
	int insert(Event event);
	void update(@Param("event_seq") int event_seq,
			@Param("event") Event event);
	void delete(int event_seq);
	Event detail(int event_seq);
	List<Event> selectEventList(Event event);
}
