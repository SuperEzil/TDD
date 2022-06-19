package com.example.tdd.controller.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true, description = "OpneApiDoc 열거형 파리미터 정의" +
        "<p>Type1 : 모드1 설명" +
        "<p>Type3 : 모드2 설명" +
        "<p>Type3 : 모드3 설명" )
public enum enumValue {
    /**
     * asdflkjlaskjdf
     */
    Type1,
    Type2,
    Type3

}
