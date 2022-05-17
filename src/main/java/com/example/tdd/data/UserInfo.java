package com.example.tdd.data;

import lombok.Data;

/**
 * 사용자 상세 정보
 */
@Data
public class UserInfo {

    /**
     * 사용자 명
     */
    private String userName;

    /**
     * 비밀 번호
     */
    private String password;

}
