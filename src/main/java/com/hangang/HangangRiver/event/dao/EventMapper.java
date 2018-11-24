package com.hangang.HangangRiver.event.dao;

import com.hangang.HangangRiver.event.model.Event;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventMapper {
	int insert(Event event);
	void update(@Param("event_seq") int event_seq,
                @Param("event") Event event);
	void delete(int event_seq);
	Event detail(int event_seq);
	List<Event> selectEventList(Event event);
}
