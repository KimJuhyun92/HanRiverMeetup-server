package com.hangang.HangangRiver.exceptions;

public class AlreadyContactedMeetingException extends Exception {
    @Override
    public String getMessage() {
        return "이미 매칭된 모임입니다.";
    }
}
