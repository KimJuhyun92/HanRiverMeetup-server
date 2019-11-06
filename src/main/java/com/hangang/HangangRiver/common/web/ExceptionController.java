package com.hangang.HangangRiver.common.web;

import com.hangang.HangangRiver.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {
    private static final Logger logger = LogManager.getLogger(ExceptionController.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DuplicatedMeetingException.class)
    public String handleDuplicatedMeetingException(DuplicatedMeetingException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AlreadyContactedMeetingException.class)
    public String handleBaseException(AlreadyContactedMeetingException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ExistJoinDetailException.class)
    public String handleBaseException(ExistJoinDetailException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidMeetingException.class)
    public String handleBaseException(InvalidMeetingException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidMyMeetingException.class)
    public String handleBaseException(InvalidMyMeetingException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidMatchingInfoException.class)
    public String handleBaseException(InvalidMatchingInfoException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RequestFailException.class)
    public String handleBaseException(RequestFailException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = LoginValidateException.class)
    public String handleBaseException(LoginValidateException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ExistUserNickNameException.class)
    public String handleBaseException(ExistUserNickNameException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotExistUserException.class)
    public String handleBaseException(NotExistUserException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotExistFacebookUserException.class)
    public String handleBaseException(NotExistFacebookUserException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = OverCountJoinDetailException.class)
    public String handleBaseException(OverCountJoinDetailException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidMeetingDetailException.class)
    public String handleBaseException(InvalidMeetingDetailException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidEventException.class)
    public String handleBaseException(InvalidEventException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = Exception.class)
//    public String handleBaseException(Exception e){
//        logger.error(e.getMessage(), e);
//        return "서버에서 예외가 발생했습니다. 확인해주세요";
//    }
}
