package com.hangang.HangangRiver.meeting.dao;


import java.util.List;

import com.hangang.HangangRiver.meeting.model.NotificationLog;

public interface NotificationLogMapper {
	int insert(NotificationLog notificationLog);
	List<NotificationLog> selectNotificationLogList(String user_id);
}
