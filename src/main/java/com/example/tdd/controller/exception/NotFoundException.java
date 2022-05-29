package com.example.tdd.controller.exception;

import com.example.tdd.controller.exception.enums.ResponseMessage;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;


public class NotFoundException extends BaseResponseException {

    @Builder
    public NotFoundException(String message, String detailMessage) {
        super(StringUtils.hasText(message)?message: ResponseMessage.NOT_FOUND.message()
                ,HttpStatus.NOT_FOUND
                , StringUtils.hasText(detailMessage)?detailMessage:null);
    }

}
