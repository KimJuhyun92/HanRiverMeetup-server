package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.meeting.dao.JoinDetailMapper;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingGuestService {
    @Autowired
    JoinDetailMapper joinDetailMapper;

    public void join(JoinDetail joinDetail){
        joinDetailMapper.insert(joinDetail);
    }

    public void cancleJoin(int application_seq){
        joinDetailMapper.delete(application_seq);
    }

    public List<JoinDetail> getJoinDetailsByMeetingId(int meeting_seq){
        return joinDetailMapper.getJoinDetails(meeting_seq);
    }
}
