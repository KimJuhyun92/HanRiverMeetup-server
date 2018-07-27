package com.hangang.HangangRiver.exceptions;

public class DuplicatedMeetingException extends Exception {
    @Override
    public String getMessage() {
        return "중복된 미팅입니다.";
    }
}
