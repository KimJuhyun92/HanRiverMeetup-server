package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.access.dao.AccessMapper;
import com.hangang.HangangRiver.meeting.dao.CommentMapper;
import com.hangang.HangangRiver.meeting.dao.JoinDetailMapper;
import com.hangang.HangangRiver.meeting.dao.MatchingMapper;
import com.hangang.HangangRiver.meeting.dao.MeetingDetailMapper;
import com.hangang.HangangRiver.meeting.dao.NotificationLogMapper;

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
}
