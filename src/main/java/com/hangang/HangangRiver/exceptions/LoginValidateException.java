package com.hangang.HangangRiver.exceptions;

public final class LoginValidateException extends Exception {
	@Override
	public String getMessage() {
		return "유효하지 않는 사용자입니다.";
	}
}
