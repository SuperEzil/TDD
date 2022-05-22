package com.example.tdd.data;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Api(tags = "Response", value = "UserResponse")
@Data
public class UserResponse {

    /**
     * 사용자명
     */

    //@ApiModelProperty(value = "사용자 명", required = true)
    //@NotNull(message = "NotNull.taskDTO.description")
    //@Size(min = 1, max = 64, message = "Size.taskDTO.description")
    @Schema(description = "사용자 명", example = "jack", nullable = false)
    private String userName;

    /**
     * 비밀 번호
     */
    @Schema(description = "비밀 번호", example = "abc1234", nullable = false)
    private String password;
}
