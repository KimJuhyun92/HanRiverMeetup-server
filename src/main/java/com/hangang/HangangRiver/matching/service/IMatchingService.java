package com.hangang.HangangRiver.matching.service;

import com.hangang.HangangRiver.matching.model.ContactedMeeting;

public interface IMatchingService {
    ContactedMeeting save(ContactedMeeting meeting);
    void remove(int contact_seq);
    ContactedMeeting getContactedMeetingById(int contact_seq);
    ContactedMeeting getContactedMeetingByMatchingInfo(int meeting_seq, int application_seq);
}
