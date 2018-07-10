package com.hangang.HangangRiver.exceptions;

public class RequestFailException extends Exception{
    @Override
    public String getMessage() {
        return "요청을 실패했습니다.";
    }
}
