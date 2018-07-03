package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.service.MeetingGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class MeetingGuestController {
    @Autowired
    MeetingGuestService meetingGuestService;

    @PostMapping("/join")
    private void joinMeeting(HttpServletRequest request, @RequestBody JoinDetail joinDetail){
        try {
            meetingGuestService.join(joinDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/request/{application_seq}")
    private void remove(@PathVariable int application_seq) throws Exception{
        meetingGuestService.cancleJoin(application_seq);
    }

    @GetMapping("/requests/{meeting_seq}")
    private List<JoinDetail> getJoinRequests(@PathVariable int meeting_seq) throws Exception{
        return meetingGuestService.getJoinDetailsByMeetingId(meeting_seq);
    }
}
