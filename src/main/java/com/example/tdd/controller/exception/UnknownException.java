package com.example.tdd.controller.exception;

import com.example.tdd.controller.exception.enums.ResponseMessage;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class UnknownException extends BaseResponseException {
    @Builder
    public UnknownException(String message, String detailMessage) {
        super(StringUtils.hasText(message) ? message : ResponseMessage.UNKNOWN.message()
                , HttpStatus.INTERNAL_SERVER_ERROR
                , StringUtils.hasText(detailMessage) ? detailMessage : null);
    }

}
