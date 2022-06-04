package com.example.tdd.controller;

import com.example.tdd.controller.exception.ConflictException;
import com.example.tdd.data.AccountRepository;
import com.example.tdd.data.entity.Account;
import com.example.tdd.data.entity.AccountListener;
import com.example.tdd.service.AccountService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("Testcontainers")
@Testcontainers
public class TestcontainersTest {

    @Mock
    AccountListener accountListener;

    @Autowired
    AccountRepository accountRepository;


    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres").withDatabaseName("TDD");

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
        System.out.println(postgreSQLContainer.getJdbcUrl());
    }


    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();

    }

    @Test
    void createAccount() throws ConflictException {

        AccountService service =  new AccountService(accountRepository, accountListener);

        Account account = Account.builder()
                .name("use1")
                .password("pass1").build();

        //Account result = accountRepository.save(account);
        Account result = service.create("aa", "aa123");


        Assertions.assertEquals("aa", result.getName(), "가입 요청한 이름과 같아야 한다.");

        assertNotNull(result);
    }

}
