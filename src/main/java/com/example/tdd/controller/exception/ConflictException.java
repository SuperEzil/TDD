package com.example.tdd.controller.exception;

import com.example.tdd.controller.exception.enums.ResponseMessage;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * 중복 생성 예와 상황 <br>
 * <li> HTTP 상태값: {@link HttpStatus#CONFLICT}
 * <li> 기본 값: {@link ResponseMessage#CONFLICT}
 * <li> 기본 메시지: {@see  ResponseMessage#CONFLICT}
 */
public class ConflictException extends BaseResponseException {

    @Builder
    public ConflictException(String message, String detailMessage) {
        super(StringUtils.hasText(message)?message: ResponseMessage.CONFLICT.message()
                ,HttpStatus.CONFLICT
                , StringUtils.hasText(detailMessage)?detailMessage:null);
    }

}
