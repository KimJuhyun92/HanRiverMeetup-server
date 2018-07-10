package com.hangang.HangangRiver.exceptions;

public class InvalidMatchingInfoException extends Exception {
    @Override
    public String getMessage() {
        return "잘못된 매칭 정보입니다.";
    }
}
