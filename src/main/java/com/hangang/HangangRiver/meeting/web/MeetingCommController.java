package com.hangang.HangangRiver.meeting.web;

import com.hangang.HangangRiver.exceptions.AlreadyContactedMeetingException;
import com.hangang.HangangRiver.exceptions.InvalidMatchingInfoException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingException;
import com.hangang.HangangRiver.meeting.model.Comment;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
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
    private ResponseEntity<Comment> createComment(HttpServletRequest request, @RequestBody Comment comment)
            throws InvalidMeetingException {
        Comment createdComment = meetingCommService.createComment(comment);
        return ResponseEntity.ok().body(createdComment);
    }

    @DeleteMapping("/comment/{comment_seq}")
    private ResponseEntity<Object> removeComment(@PathVariable int comment_seq)
            throws Exception{
        meetingCommService.removeComment(comment_seq);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/comments/{meeting_seq}")
    private ResponseEntity<List<Comment>> getComments(@PathVariable int meeting_seq)
            throws Exception{
        List<Comment> comments = meetingCommService.getCommentsByMeeting(meeting_seq);
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping("/match")
    private ResponseEntity<ContactedMeeting> match(HttpServletRequest request, @RequestBody ContactedMeeting meeting)
            throws AlreadyContactedMeetingException, InvalidMatchingInfoException {
        ContactedMeeting contactedMeeting = meetingCommService.match(meeting);
        return ResponseEntity.ok().body(contactedMeeting);
    }

    @GetMapping("/match/{contact_seq}")
    private ResponseEntity<ContactedMeeting> contactedMeetingDetail(@PathVariable int contact_seq) {
        ContactedMeeting contactedMeeting = meetingCommService.getContactedMeetingById(contact_seq);
        return ResponseEntity.ok().body(contactedMeeting);
    }

    @PostMapping("/match/find")
    private ResponseEntity<ContactedMeeting> contactedMeetingDetailByMachingInfo( HttpServletRequest request,
                                                                                  @RequestBody ContactedMeeting meeting) {
        ContactedMeeting contactedMeeting = meetingCommService.getContactedMeetingByMatchingInfo(
                meeting.getMeeting_seq(),
                meeting.getApplication_seq()
        );
        return ResponseEntity.ok().body(contactedMeeting);
    }

    @DeleteMapping("/match/{contact_seq}")
    private ResponseEntity<Object> unmatch(@PathVariable int contact_seq) {
        meetingCommService.unmatch(contact_seq);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/matchings/{user_id}")
    private ResponseEntity<List<MeetingDetail>> getMyMatchings(@PathVariable String user_id) {
		List<MeetingDetail> myMatchingList = meetingCommService.selectMyMatchings(user_id);
		return ResponseEntity.ok().body(myMatchingList);
    }

/*    @DeleteMapping("/match/{user_id}/{user_type}/{meeting_seq}")
    private ResponseEntity<Object> remove(@PathVariable String user_id, @PathVariable String user_type) throws Exception{
    	meetingCommService.removeMatching();
        return ResponseEntity.ok().body(true);
    }아이디와 유저타입을 가지고 호스트라면 미팅 seq를 지우면되고 게스트라면 미팅seq와 아이디를 어플리케이션디비에서 검색해서 ??정의가안됨..*/
}
