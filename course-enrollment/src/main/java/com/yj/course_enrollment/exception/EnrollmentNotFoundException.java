package com.yj.course_enrollment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class EnrollmentNotFoundException extends RuntimeException{
    public  EnrollmentNotFoundException(String message){
        super(message);
    }
}