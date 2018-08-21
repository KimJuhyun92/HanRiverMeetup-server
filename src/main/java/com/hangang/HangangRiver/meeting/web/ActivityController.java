package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.meeting.model.Activity;
import com.hangang.HangangRiver.meeting.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @GetMapping("/all")
    private ResponseEntity<List<Activity>> getAllActivities() {
        return ResponseEntity.ok().body(activityService.getAllActivities());
    }
}