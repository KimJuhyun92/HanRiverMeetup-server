package com.hangang.HangangRiver.exceptions;

public class NotExistFacebookUserException extends Exception{
    @Override
    public String getMessage() {
        return "페이스북에 가입되지 않은 아이디 입니다.";
    }
}
