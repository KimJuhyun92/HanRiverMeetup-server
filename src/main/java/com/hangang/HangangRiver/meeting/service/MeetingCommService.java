package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.exceptions.AlreadyContactedMeetingException;
import com.hangang.HangangRiver.exceptions.InvalidMatchingInfoException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingException;
import com.hangang.HangangRiver.meeting.model.Comment;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingCommService extends MeetingBaseService{
    public Comment createComment(Comment comment) throws InvalidMeetingException {
        // 1. Check the target meeting to be valid
        int meeting_seq = comment.getMeeting_seq();

        if (meetingDetailMapper.isExistMeetingDetail(meeting_seq)) {
            commentMapper.insert(comment);
            return comment;
        }
        else {
            throw new InvalidMeetingException();
        }
    }

    public List<Comment> getCommentsByMeeting(int meeting_seq){
        return commentMapper.selectCommentList(meeting_seq);
    }

    public void removeComment(int comment_seq){
        commentMapper.delete(comment_seq);
    }

    public ContactedMeeting match(ContactedMeeting meeting)
            throws InvalidMatchingInfoException, AlreadyContactedMeetingException {

        int meeting_seq = meeting.getMeeting_seq();
        int application_seq = meeting.getApplication_seq();
        JoinDetail joinDetail = joinDetailMapper.getJoinDetail(application_seq);

        if(meetingDetailMapper.isExistMeetingDetail(meeting_seq) == false
                || joinDetail == null
                || joinDetail.getMeeting_seq() != meeting_seq){
            throw new InvalidMatchingInfoException();
        }

        if(matchingMapper.isContactedMeeting(meeting_seq)){
            throw new AlreadyContactedMeetingException();
        }

        matchingMapper.insert(meeting);
        return meeting;
    }

    public ContactedMeeting getContactedMeetingById(int contact_seq){
        return matchingMapper.detail(contact_seq);
    }

    public ContactedMeeting getContactedMeetingByMatchingInfo(int meeting_seq, int application_seq){
        return matchingMapper.detailByMatchingInfo(meeting_seq, application_seq);
    }

    public void unmatch(int contact_seq){
        matchingMapper.delete(contact_seq);
    }

    // TODO : User ID에 따른 모든 매칭된 미팅들을 가지고 와야할듯?
}
