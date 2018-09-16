package com.hangang.HangangRiver.exceptions;

public class NotExistUserException extends Exception{
    @Override
    public String getMessage() {
        return "가입되지 않은 아이디 입니다.";
    }
}
