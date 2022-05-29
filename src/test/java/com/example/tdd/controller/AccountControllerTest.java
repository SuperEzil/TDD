package com.example.tdd.controller;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.RepeatedTest.LONG_DISPLAY_NAME;

class AccountControllerTest {


    @Tag("Tag01")
    @Test
    @DisplayName("사용자 가입")
    void createUser() {
        System.out.println("createUser");
    }

    @Tag("Tag01")
    @Test
    @DisplayName("사용자 조회 🔍")
    void getUser() {
    }

    @Tag("Repeated")
    @DisplayName("사용자 조회 반복")
    @RepeatedTest(value = 5, name = LONG_DISPLAY_NAME)
    void repeatedGetUser(RepetitionInfo repetitionInfo) {
        System.out.println("repeatedGetUser("+repetitionInfo.getCurrentRepetition()+"/"+repetitionInfo.getTotalRepetitions()+")");
    }


    @Tag("Tag02")
    @Test
    @DisplayName("사용자 정보 변경")
    void modifyUser() {
    }

    @Tag("Tag02")
    @Test
    @DisplayName("사용자 삭제")
    void deleteUser() {
    }


    ////////////////////////////////////////////////////
    @BeforeAll
    static void beforeAll(){
        System.out.println("BeforeAll");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("AfterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach");
    }



}