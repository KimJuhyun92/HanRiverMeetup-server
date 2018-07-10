package com.hangang.HangangRiver.meeting.web;

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
    private ResponseEntity<JoinDetail> joinMeeting(HttpServletRequest request, @RequestBody JoinDetail joinDetail){
        try {
            JoinDetail createdJoinDetail = meetingGuestService.join(joinDetail);
            return ResponseEntity.ok().body(createdJoinDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/request/{application_seq}")
    private ResponseEntity<Object> remove(@PathVariable int application_seq) throws Exception{
        try {
            meetingGuestService.cancleJoin(application_seq);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/requests/{meeting_seq}")
    private ResponseEntity<List<JoinDetail>> getJoinRequests(@PathVariable int meeting_seq) throws Exception{
        try{
            List<JoinDetail> requests = meetingGuestService.getJoinDetailsByMeetingId(meeting_seq);
            return ResponseEntity.ok().body(requests);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
