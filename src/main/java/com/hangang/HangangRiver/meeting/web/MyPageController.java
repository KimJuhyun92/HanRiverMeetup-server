package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.exceptions.DuplicatedMeetingException;
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

    @GetMapping("/{userID}/matchings")
    private ResponseEntity<List<MeetingDetail>> getMyMatchings(@PathVariable String user_id) {
        List<MeetingDetail> myMatchingList = myPageService.getMyMatchings(user_id);
        return ResponseEntity.ok().body(myMatchingList);
    }

    @GetMapping("/{user_id}/meetings")
    @ResponseBody
    private ResponseEntity<List<MeetingDetail>> getMyMeetings(@PathVariable String user_id) throws DuplicatedMeetingException {
        List<MeetingDetail> myMeetingList = myPageService.getMyMeetings(user_id);
        if(myMeetingList != null){
            return ResponseEntity.ok().body(myMeetingList);
        }
        return ResponseEntity.badRequest().body(null);
    }
}