package com.hangang.HangangRiver.meeting.model;

import java.util.Date;

public class ContactedMeeting {
    private int contact_seq;
    private int meeting_seq;
    private int application_seq;
    private Date contact_time;

    public ContactedMeeting() {}

    public ContactedMeeting(int contact_seq, int meeting_seq, int application_seq, Date contact_time){
        this.contact_seq = contact_seq;
        this.meeting_seq = meeting_seq;
        this.application_seq = application_seq;
        this.contact_time = contact_time;
    }

    public int getContact_seq() {
        return contact_seq;
    }

    public void setContact_seq(int contact_seq) {
        this.contact_seq = contact_seq;
    }

    public int getMeeting_seq() {
        return meeting_seq;
    }

    public void setMeeting_seq(int meeting_seq) {
        this.meeting_seq = meeting_seq;
    }

    public int getApplication_seq() {
        return application_seq;
    }

    public void setApplication_seq(int application_seq) {
        this.application_seq = application_seq;
    }

    public Date getContact_time() {
        return contact_time;
    }

    public void setContact_time(Date contact_time) {
        this.contact_time = contact_time;
    }
}
