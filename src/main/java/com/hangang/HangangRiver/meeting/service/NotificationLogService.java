package com.hangang.HangangRiver.meeting.service;


import com.hangang.HangangRiver.meeting.model.NotificationLog;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationLogService extends MeetingBaseService{
    public NotificationLog createNotificationLog(NotificationLog notificationLog){
            notificationLogMapper.insert(notificationLog);
            return notificationLog;
    }

    public List<NotificationLog> getNotificationLogById(String user_id){
        return notificationLogMapper.selectNotificationLogList(user_id);
    }
}
