package com.example.tdd.controller.exception;

import com.example.tdd.controller.exception.enums.ResponseMessage;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;


public class BadRequestException extends BaseResponseException {

    @Builder
    public BadRequestException(String message, String detailMessage) {
        super(StringUtils.hasText(message)?message: ResponseMessage.BAD_REQUEST.message()
                ,HttpStatus.BAD_REQUEST
                , StringUtils.hasText(detailMessage)?detailMessage:null);
    }

}
