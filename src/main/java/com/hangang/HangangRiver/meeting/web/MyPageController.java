package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.exceptions.DuplicatedMeetingException;
import com.hangang.HangangRiver.exceptions.InvalidMatchingInfoException;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.service.MyPageService;

import org.json.simple.JSONObject;
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
    private ResponseEntity<List<JSONObject>> getHopeMeetings(@PathVariable String userID) {
        return ResponseEntity.ok().body(myPageService.getHopeMeetings(userID));
    }

    @GetMapping("/{userID}/matchings")
    private ResponseEntity<List<JSONObject>> getMyMatchings(@PathVariable String userID) throws InvalidMatchingInfoException {
    	List<JSONObject> myMatchingList = myPageService.getMyMatchings(userID);
    	return ResponseEntity.ok().body(myMatchingList);
    }

    @GetMapping("/{userID}/meetings")
    @ResponseBody
    private ResponseEntity<List<MeetingDetail>> getMyMeetings(@PathVariable String userID) throws DuplicatedMeetingException {
        List<MeetingDetail> myMeetingList = myPageService.getMyMeetings(userID);
        if(myMeetingList != null){
            return ResponseEntity.ok().body(myMeetingList);
        }
        return ResponseEntity.badRequest().body(null);
    }

}