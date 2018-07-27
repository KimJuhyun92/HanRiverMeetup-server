package com.hangang.HangangRiver.exceptions;

public class DuplicatedMeetingException extends Exception {
    @Override
    public String getMessage() {
        return "미팅은 하루에 한개만 개설가능합니다.";
    }
}
