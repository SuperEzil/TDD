package com.example.tdd.controller;

import com.example.tdd.tags.SlowTag;
import org.junit.jupiter.api.*;

class AccountControllerTest {

    @SlowTag
    @Test
    @DisplayName("사용자 가입")
    void createUser() {
        System.out.println("createUser");
    }

    @Tag("slow")
    @Test
    @DisplayName("사용자 조회 🔍")
    void getUser() {

    }


    @Test
    @DisplayName("사용자 정보 변경")    void modifyUser() {
    }

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