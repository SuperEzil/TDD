package com.example.tdd.controller.exception.enums;

/**
 * Response 응답 메시지 목록
 * <li> {@link ResponseMessage#NOT_FOUND  NOT_FOUND : "대상을 찾을수 없습니다."}
 * <li> {@link ResponseMessage#NOT_MODIFIED  NOT_MODIFIED : "변경 할수 없습니다."}
 * <li> {@link ResponseMessage#CONFLICT  CONFLICT : "데이터 출돌이 발생하였습니다."}
 * <li> {@link ResponseMessage#BAD_REQUEST  BAD_REQUEST : "잘못된 요청입니다."}
 * <li> {@link ResponseMessage#UNKNOWN  UNKOWN : "알수 없는 오류 발생"}
 */
public enum ResponseMessage {
    /** 변경 할수 없습니다. */
    NOT_MODIFIED("변경 할수 없습니다."),
    /** 잘못된 요청입니다. */
    BAD_REQUEST("잘못된 요청입니다."),
    /** 대상을 찾을수 없습니다. */
    NOT_FOUND("대상을 찾을수 없습니다."),
    /**  데이터 출돌이 발생하였습니다.   */
    CONFLICT("데이터 출돌이 발생하였습니다."),
    /**  알수 없는 오류 발생   */
    UNKNOWN("알수 없는 오류 발생");

    private final String _msg;

    ResponseMessage(String msg) {
        this._msg = msg;
    }

    public String message() {
        return this._msg;
    }
}
