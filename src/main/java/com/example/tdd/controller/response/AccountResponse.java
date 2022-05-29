package com.example.tdd.controller.response;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Api(tags = "Response", value = "AccountResponse")
@Getter
@SuperBuilder
public class AccountResponse extends BaseResponse{

    /**
     * 사용자명
     */
    //@NotNull
    //@Size(min = 4, max = 8)
    @Schema(description = "사용자 명", example = "jack")
    private String userName;

    /**
     * 비밀 번호
     */
    @Schema(description = "비밀 번호", example = "abc1234")
    private String password;
}
