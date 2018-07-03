package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.meeting.model.Comment;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import com.hangang.HangangRiver.meeting.service.MeetingCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comm")
public class MeetingCommController {
    @Autowired
    MeetingCommService meetingCommService;

    @PostMapping("/comment")
    private void createComment(HttpServletRequest request, @RequestBody Comment comment){
        try {
            meetingCommService.createComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/comment/{comment_seq}")
    private void removeComment(@PathVariable int comment_seq) throws Exception{
        meetingCommService.removeComment(comment_seq);
    }

    @GetMapping("/comments/{meeting_seq}")
    private List<Comment> getComments(@PathVariable int meeting_seq) throws Exception{
        return meetingCommService.getCommentsByMeeting(meeting_seq);
    }

//    @PostMapping("/match")
//    private ContactedMeeting saveMeeting(HttpServletRequest request, @RequestBody ContactedMeeting meeting){
//        try {
//            return matchingService.save(meeting);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @GetMapping("/match/{contact_seq}")
    private ContactedMeeting contactedMeetingDetail(@PathVariable int contact_seq) throws Exception{
        return meetingCommService.getContactedMeetingById(contact_seq);
    }

    @PostMapping("/match/find")
    private ContactedMeeting contactedMeetingDetailByMachingInfo( HttpServletRequest request,
                                                                  @RequestBody ContactedMeeting meeting) throws Exception{
        try {
            return meetingCommService.getContactedMeetingByMatchingInfo(
                    meeting.getMeeting_seq(),
                    meeting.getApplication_seq()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @DeleteMapping("/match/{contact_seq}")
//    private void unmatch(@PathVariable int contact_seq) throws Exception{
//        //meetingCommService.unmatch(contact_seq);
//    }
}
