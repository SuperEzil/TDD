package com.example.tdd.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Schema
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class BaseResponse {

    @Schema(description = "기본 메시지")
    public String message;
    @Schema(description = "상세 메시지")
    public String detailMessage;
}
