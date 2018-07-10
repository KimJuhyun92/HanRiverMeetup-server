package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.exceptions.DuplicatedMeetingException;
import com.hangang.HangangRiver.meeting.dao.MeetingDetailMapper;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class MeetingHostService extends MeetingBaseService {

    public MeetingDetail createMeeting(MeetingDetail meetingDetail)
            throws DuplicatedMeetingException {

        boolean isDuplicated = meetingDetailMapper.isDuplicatedDetail(meetingDetail.getMeeting_time(), meetingDetail.getUser_id());

        if(isDuplicated){
            throw new DuplicatedMeetingException();
        }
        else{
            meetingDetailMapper.insert(meetingDetail);
            return meetingDetail;
        }
    }

    public MeetingDetail getMeetingDetailById(int meeting_seq){
        return meetingDetailMapper.detail(meeting_seq);
    }

    public MeetingDetail modifyMeeting(int meeting_seq, MeetingDetail meetingDetail){
        meetingDetailMapper.update(meeting_seq, meetingDetail);
        return meetingDetailMapper.detail(meeting_seq);
    }

    public void removeMeeting(int meeting_seq){
        MeetingDetail meetingDetail = meetingDetailMapper.detail(meeting_seq);
        meetingDetailMapper.move(meetingDetail);
        meetingDetailMapper.delete(meeting_seq);
    }

    public List<MeetingDetailForm> selectTodayMeeting(MeetingDetailForm meetingForm){
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date ();
        String time = SimpleDateFormat.format ( currentTime );

        meetingForm.setStartTime(time+" 00:00:00");
        meetingForm.setEndTime(time+" 23:59:59");
        return meetingDetailMapper.selectAll(meetingForm);
    }
}