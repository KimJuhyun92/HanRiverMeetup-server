package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.meeting.dao.ActivityMapper;
import com.hangang.HangangRiver.meeting.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    public ActivityMapper activityMapper;

    public List<Activity> getAllActivities() {
        return activityMapper.getAllActivities();
    }
}
