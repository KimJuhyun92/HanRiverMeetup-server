package com.hangang.HangangRiver.common.web;

import com.hangang.HangangRiver.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DuplicatedMeetingException.class)
    public String handleDuplicatedMeetingException(DuplicatedMeetingException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AlreadyContactedMeetingException.class)
    public String handleBaseException(AlreadyContactedMeetingException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ExistJoinDetailException.class)
    public String handleBaseException(ExistJoinDetailException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidMeetingException.class)
    public String handleBaseException(InvalidMeetingException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidMatchingInfoException.class)
    public String handleBaseException(InvalidMatchingInfoException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RequestFailException.class)
    public String handleBaseException(RequestFailException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = LoginValidateException.class)
    public String handleBaseException(LoginValidateException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ExistUserNickNameException.class)
    public String handleBaseException(ExistUserNickNameException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public String handleBaseException(Exception e){
        return "서버에서 예외가 발생했습니다. 확인해주세요";
    }
}
