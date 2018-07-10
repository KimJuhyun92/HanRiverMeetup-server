package com.hangang.HangangRiver.exceptions;

public class ExistJoinDetailException extends Exception{
    @Override
    public String getMessage() {
        return "이미 모임에 지원했습니다.";
    }
}
