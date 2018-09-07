package com.hangang.HangangRiver.timeline.dao;

import java.util.List;

import com.hangang.HangangRiver.timeline.model.TimeLine;
import com.hangang.HangangRiver.timeline.model.TimeLineForm;

public interface TimeLineMapper {
	int insert(TimeLine timeLine);
	void delete(int timeLine_seq);
	List<TimeLineForm> selectTimeLineList(TimeLineForm timeLineForm);
}
