package com.hangang.HangangRiver.exceptions;

public class InvalidJsonBody extends Exception {
    @Override
    public String getMessage() {
        return "잘못된 json body값 입니다.";
    }
}
