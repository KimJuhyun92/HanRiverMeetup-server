package com.hangang.HangangRiver.exceptions;

public class ExistUserNickNameException extends Exception{
    @Override
    public String getMessage() {
        return "이미 존재하는 닉네임 입니다.";
    }
}
