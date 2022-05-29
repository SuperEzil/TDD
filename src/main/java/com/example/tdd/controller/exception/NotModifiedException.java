package com.example.tdd.controller.exception;

import com.example.tdd.controller.exception.enums.ResponseMessage;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class NotModifiedException extends BaseResponseException {
    @Builder
    public NotModifiedException(String message, String detailMessage) {
        super(StringUtils.hasText(message) ? message : ResponseMessage.NOT_MODIFIED.message()
                , HttpStatus.NOT_MODIFIED
                , StringUtils.hasText(detailMessage) ? detailMessage : null);
    }

}
