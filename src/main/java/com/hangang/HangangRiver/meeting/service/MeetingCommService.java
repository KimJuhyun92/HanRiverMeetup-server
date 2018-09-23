package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.access.model.User;
import com.hangang.HangangRiver.common.web.MessageManager;
import com.hangang.HangangRiver.exceptions.AlreadyContactedMeetingException;
import com.hangang.HangangRiver.exceptions.InvalidMatchingInfoException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingException;
import com.hangang.HangangRiver.meeting.model.Comment;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.model.NotificationLog;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MeetingCommService extends MeetingBaseService{
    public Comment createComment(Comment comment) throws InvalidMeetingException, IOException {
        // 1. Check the target meeting to be valid
        int meeting_seq = comment.getMeeting_seq();

        if (meetingDetailMapper.isExistMeetingDetail(meeting_seq)) {
            commentMapper.insert(comment);

            MeetingDetail meetingDetail = meetingDetailMapper.detail(meeting_seq);
            User user = accessMapper.detail(meetingDetail.getUser_id());
            String message = "누가 내가 만든 모임에 관심을 보여요.";
            MessageManager.sendCommonMessage(user.getFcm_token(), "심심한강", message);

            NotificationLog notificationLog = new NotificationLog();
            notificationLog.setUser_id(meetingDetail.getUser_id());
            notificationLog.setMessage(message);
            notificationLogMapper.insert(notificationLog);
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
            throws InvalidMatchingInfoException, AlreadyContactedMeetingException, IOException {

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

        MeetingDetail meetingInfo = meetingDetailMapper.detail(meeting.getMeeting_seq());
        meeting.setHost_user_id(meetingInfo.getUser_id());
        JoinDetail joinDetailInfo = joinDetailMapper.getJoinDetail(meeting.getApplication_seq());
        meeting.setGuest_user_id(joinDetailInfo.getUser_id());
        matchingMapper.insert(meeting);

        User hostUser = accessMapper.detail(meeting.getHost_user_id());
        User guestUser = accessMapper.detail(meeting.getGuest_user_id());
        String message = "같이 놀강~ 난누굴강 완료모임을 확인해주세요.";
        MessageManager.sendCommonMessage(hostUser.getFcm_token(), "심심한강", message);
        MessageManager.sendCommonMessage(guestUser.getFcm_token(), "심심한강", message);

        NotificationLog hostNotificationLog = new NotificationLog();
        hostNotificationLog.setUser_id(meeting.getHost_user_id());
        hostNotificationLog.setMessage(message);
        notificationLogMapper.insert(hostNotificationLog);

        NotificationLog guestNotificationLog = new NotificationLog();
        guestNotificationLog.setUser_id(meeting.getGuest_user_id());
        guestNotificationLog.setMessage(message);
        notificationLogMapper.insert(guestNotificationLog);

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

/*    public List<MeetingDetail> selectMyMatchingMeetings(String user_id){
        return matchingMapper.selectMyMatchingMeetings(user_id);
    }

    public List<MeetingDetail> selectMyMatchingApplications(String user_id){
        return matchingMapper.selectMyMatchingApplications(user_id);
    }*/
}
