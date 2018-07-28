package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypage")
public class MyPageController {
    @Autowired
    MyPageService myPageService;

    @GetMapping("/{userID}/join/meetings")
    @ResponseBody
    private ResponseEntity<List<MeetingDetail>> getHopeMeetings(@PathVariable String userID) {
        return ResponseEntity.ok().body(myPageService.getHopeMeetings(userID));
    }
}