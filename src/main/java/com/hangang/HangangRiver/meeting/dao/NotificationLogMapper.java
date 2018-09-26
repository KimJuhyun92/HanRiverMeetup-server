package com.hangang.HangangRiver.meeting.dao;


import java.util.List;

import com.hangang.HangangRiver.meeting.model.NotificationLog;
import org.apache.ibatis.annotations.Param;

public interface NotificationLogMapper {
	int insertDetail(NotificationLog notificationLog);
	int insert(@Param("user_id") String user_id,
			   @Param("message") String message);
	List<NotificationLog> selectNotificationLogList(String user_id);
}
