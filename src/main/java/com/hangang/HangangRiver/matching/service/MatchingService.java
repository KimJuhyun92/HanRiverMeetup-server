package com.hangang.HangangRiver.matching.service;

import com.hangang.HangangRiver.matching.dao.MatchingMapper;
import com.hangang.HangangRiver.matching.model.ContactedMeeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchingService implements IMatchingService{
    @Autowired
    private MatchingMapper matchingMapper;

    public ContactedMeeting save(ContactedMeeting meeting){
        matchingMapper.insert(meeting);
        return getContactedMeetingByMatchingInfo(meeting.getMeeting_seq(), meeting.getApplication_seq());
    }

    public ContactedMeeting getContactedMeetingById(int contact_seq){
        return matchingMapper.detail(contact_seq);
    }

    public ContactedMeeting getContactedMeetingByMatchingInfo(int meeting_seq, int application_seq){
        return matchingMapper.detailByMatchingInfo(meeting_seq, application_seq);
    }

    public void remove(int contact_seq){
        matchingMapper.delete(contact_seq);
    }
}
