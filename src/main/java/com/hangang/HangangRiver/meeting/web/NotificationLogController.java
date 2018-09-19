package com.hangang.HangangRiver.meeting.web;


import com.hangang.HangangRiver.meeting.model.NotificationLog;
import com.hangang.HangangRiver.meeting.service.NotificationLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificationLog")
public class NotificationLogController {
    @Autowired
    NotificationLogService notificationLogService;

    @GetMapping("/{userID}")
    private ResponseEntity<List<NotificationLog>> getNotificationLog(@PathVariable String userID){
    	List<NotificationLog> myNotificationLogList = notificationLogService.getNotificationLogById(userID);
    	return ResponseEntity.ok().body(myNotificationLogList);
    }
}