package com.hangang.HangangRiver.exceptions;


public class InvalidMeetingDetailException extends Exception{

	private String errors;

	public InvalidMeetingDetailException (String errors){
		this.errors = errors;
	}

    @Override
    public String getMessage() {
        return errors;
    }
}
