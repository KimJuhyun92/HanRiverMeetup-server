package com.hangang.HangangRiver.exceptions;

public class InvalidEventException extends Exception {
    @Override
    public String getMessage() {
        return "존재하지 않는 이벤트입니다.";
    }
}
