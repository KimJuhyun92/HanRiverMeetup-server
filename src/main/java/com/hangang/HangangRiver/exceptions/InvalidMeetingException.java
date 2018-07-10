package com.hangang.HangangRiver.exceptions;

public class InvalidMeetingException extends Exception {
    @Override
    public String getMessage() {
        return "존재하지 않는 모임입니다.";
    }
}
