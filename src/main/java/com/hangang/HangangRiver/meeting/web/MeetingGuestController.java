package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.exceptions.DuplicatedMeetingException;
import com.hangang.HangangRiver.exceptions.ExistJoinDetailException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingException;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.service.MeetingGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class MeetingGuestController {
    @Autowired
    MeetingGuestService meetingGuestService;

    @PostMapping("/join")
    @ResponseBody
    private ResponseEntity<JoinDetail> joinMeeting(HttpServletRequest request, @RequestBody JoinDetail joinDetail)
            throws ExistJoinDetailException, InvalidMeetingException {
        JoinDetail createdJoinDetail = meetingGuestService.join(joinDetail);
        return ResponseEntity.ok().body(createdJoinDetail);
    }

    @DeleteMapping("/request/{application_seq}")
    private ResponseEntity<Object> remove(@PathVariable int application_seq) throws Exception{
        meetingGuestService.cancleJoin(application_seq);
        return ResponseEntity.ok().body(true);
    }
}
