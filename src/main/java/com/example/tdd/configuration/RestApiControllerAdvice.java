package com.example.tdd.configuration;

import com.example.tdd.controller.AccountController;
import com.example.tdd.controller.exception.BaseResponseException;
import com.example.tdd.controller.response.AccountResponse;
import com.example.tdd.controller.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

// basePackageClasses(특정할 클래스나 패키지)
@RestControllerAdvice(basePackageClasses = AccountController.class)
public class RestApiControllerAdvice {

    // 커스텀 ResponseException 메소드의 예외 처리
    @ExceptionHandler(value = BaseResponseException.class)
    public ResponseEntity BaseResponseException(BaseResponseException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(BaseResponse.builder()
                .message(e.getMessage())
                .detailMessage(e.getDetailMessage())
                .build());
    }

    /**
     * 비인가 메소드 연결 시도시 예외 이벤트
     * HttpRequestMethodNotSupportedException: Request method 'GET' not supported
     * "status": 405,
     * "error": "Method Not Allowed
     */


    // 특정 메소드의 예외 처리
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();

        BaseResponse response = BaseResponse.builder()
                .message(msg)
                .detailMessage(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e) {
        List<ConstraintViolation<?>> list = e.getConstraintViolations().stream().toList();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        String msg = list.get(0).getMessage();
        BaseResponse response = BaseResponse.builder()
                .message(msg)
                .detailMessage(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
