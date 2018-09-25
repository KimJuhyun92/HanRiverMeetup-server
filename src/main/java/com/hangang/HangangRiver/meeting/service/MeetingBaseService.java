package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.access.dao.AccessMapper;
import com.hangang.HangangRiver.access.model.User;
import com.hangang.HangangRiver.common.web.ExceptionController;
import com.hangang.HangangRiver.common.web.MessageManager;
import com.hangang.HangangRiver.meeting.dao.CommentMapper;
import com.hangang.HangangRiver.meeting.dao.JoinDetailMapper;
import com.hangang.HangangRiver.meeting.dao.MatchingMapper;
import com.hangang.HangangRiver.meeting.dao.MeetingDetailMapper;
import com.hangang.HangangRiver.meeting.dao.NotificationLogMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class MeetingBaseService {
    @Autowired
    public JoinDetailMapper joinDetailMapper;
    @Autowired
    public MeetingDetailMapper meetingDetailMapper;
    @Autowired
    public CommentMapper commentMapper;
    @Autowired
    public MatchingMapper matchingMapper;
    @Autowired
    public AccessMapper accessMapper;
    @Autowired
    public NotificationLogMapper notificationLogMapper;

    private final Logger logger = LogManager.getLogger(ExceptionController.class);

    public void pushMessage(String user_id, String message) {
        try {
            User user = accessMapper.detail(user_id);
            MessageManager.sendCommonMessage(user.getFcm_token(), "심심한강", message);

            notificationLogMapper.insert(user_id, message);
        }
        catch(Exception e) {
            logger.error(user_id + " - " + message + " : " + e.getMessage(), e);
        }
    }
}
