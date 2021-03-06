package com.example.tdd.controller.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BaseResponseException extends RuntimeException{

    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private final String detailMessage;

    public BaseResponseException(String message, HttpStatus httpStatus, String detailMessage) {
        super(message);
        this.httpStatus = httpStatus;
        this.detailMessage = detailMessage;
    }

}
