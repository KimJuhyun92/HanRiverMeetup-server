package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.meeting.model.Comment;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import com.hangang.HangangRiver.meeting.service.MeetingCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comm")
public class MeetingCommController {
    @Autowired
    MeetingCommService meetingCommService;

    @PostMapping("/comment")
    private ResponseEntity<Comment> createComment(HttpServletRequest request, @RequestBody Comment comment){
        try {
            Comment createdComment = meetingCommService.createComment(comment);
            return ResponseEntity.ok().body(createdComment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/comment/{comment_seq}")
    private ResponseEntity<Object> removeComment(@PathVariable int comment_seq) throws Exception{
        try {
            meetingCommService.removeComment(comment_seq);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/comments/{meeting_seq}")
    private ResponseEntity<List<Comment>> getComments(@PathVariable int meeting_seq) throws Exception{
        try {
            List<Comment> comments = meetingCommService.getCommentsByMeeting(meeting_seq);
            return ResponseEntity.ok().body(comments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/match")
    private ResponseEntity<ContactedMeeting> match(HttpServletRequest request, @RequestBody ContactedMeeting meeting){
        try {
            ContactedMeeting contactedMeeting = meetingCommService.match(meeting);
            return ResponseEntity.ok().body(contactedMeeting);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/match/{contact_seq}")
    private ResponseEntity<ContactedMeeting> contactedMeetingDetail(@PathVariable int contact_seq) throws Exception{
        try {
            ContactedMeeting contactedMeeting = meetingCommService.getContactedMeetingById(contact_seq);
            return ResponseEntity.ok().body(contactedMeeting);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/match/find")
    private ResponseEntity<ContactedMeeting> contactedMeetingDetailByMachingInfo( HttpServletRequest request,
                                                                  @RequestBody ContactedMeeting meeting) throws Exception{
        try {
            ContactedMeeting contactedMeeting = meetingCommService.getContactedMeetingByMatchingInfo(
                    meeting.getMeeting_seq(),
                    meeting.getApplication_seq()
            );
            return ResponseEntity.ok().body(contactedMeeting);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/match/{contact_seq}")
    private ResponseEntity<Object> unmatch(@PathVariable int contact_seq) throws Exception{
        try {
            meetingCommService.unmatch(contact_seq);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }
    }
}
