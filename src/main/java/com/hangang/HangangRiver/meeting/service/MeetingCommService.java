package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.meeting.dao.CommentMapper;
import com.hangang.HangangRiver.meeting.dao.MatchingMapper;
import com.hangang.HangangRiver.meeting.model.Comment;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingCommService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private MatchingMapper matchingMapper;

    public void createComment(Comment comment){
        commentMapper.insert(comment);
    }

    public List<Comment> getCommentsByMeeting(int meeting_seq){
        return commentMapper.selectCommentList(meeting_seq);
    }

    public void removeComment(int comment_seq){
        commentMapper.delete(comment_seq);
    }

    // public void match(int meetingGuestId, int meetingHostID)
//    public ContactedMeeting insertM(ContactedMeeting meeting){
//        matchingMapper.insert(meeting);
//        return getContactedMeetingByMatchingInfo(meeting.getMeeting_seq(), meeting.getApplication_seq());
//    }

    public ContactedMeeting getContactedMeetingById(int contact_seq){
        return matchingMapper.detail(contact_seq);
    }

    public ContactedMeeting getContactedMeetingByMatchingInfo(int meeting_seq, int application_seq){
        return matchingMapper.detailByMatchingInfo(meeting_seq, application_seq);
    }

    // public void unmatch(int contact_seq)
//    public void remove(int contact_seq){
//        matchingMapper.delete(contact_seq);
//    }
}
