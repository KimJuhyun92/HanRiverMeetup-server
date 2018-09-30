package com.hangang.HangangRiver.exceptions;

public class InvalidMyMeetingException extends Exception {
    @Override
    public String getMessage() {
        return "내가 만든 모임입니다.";
    }
}
