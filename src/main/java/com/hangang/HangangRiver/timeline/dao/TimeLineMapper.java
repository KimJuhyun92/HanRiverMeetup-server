package com.hangang.HangangRiver.timeline.dao;

import java.util.Date;
import java.util.List;

import com.hangang.HangangRiver.timeline.model.TimeLine;
import com.hangang.HangangRiver.timeline.model.TimeLineForm;
import org.apache.ibatis.annotations.Param;

public interface TimeLineMapper {
	int insert(TimeLine timeLine);
	void delete(int timeLine_seq);
	List<TimeLineForm> selectTimeLineList(TimeLineForm timeLineForm);
	List<TimeLineForm> selectTimeLineListWithOffset(@Param("creation_time") String meeting_time,
										  @Param("offset") Integer offset,
										  @Param("limit") Integer limit);
}
