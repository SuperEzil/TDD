package com.example.tdd.data;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 사용자 정보 [Request]
 * 
 */
@Data
public class UserInfo {

    /**
     * 사용자명
     */
    @NotNull
    @Size(min = 1, max = 4, message = "사용자명의 크기는 1에서 4 사이여야 합니다")
    @Schema(description = "사용자 명", example = "jack", minLength = 1, maxLength = 16)
    private String name;

    /**
     * 비밀 번호
     */
    @Schema(description = "비밀 번호", example = "abc1234")
    @Size(min = 4, max = 8, message = "비밀번호의 크기는 4에서 8 사이여야 합니다")
    private String password;
}
