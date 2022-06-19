package com.example.tdd.controller;

import com.example.tdd.controller.exception.ConflictException;
import com.example.tdd.controller.exception.NotFoundException;
import com.example.tdd.data.AccountRepository;
import com.example.tdd.data.entity.Account;
import com.example.tdd.data.entity.AccountListener;
import com.example.tdd.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("testcontainers_compose")
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(initializers = TestcontainersComposeTest.ComposePropertyInitializer.class)
class TestcontainersComposeTest {

    @Mock
    AccountListener accountListener;

    @Autowired
    AccountRepository accountRepository;

    @Container
    static DockerComposeContainer composeContainer = new DockerComposeContainer(new File("src/test/resources/test-docker-compose.yml"))
            .withExposedService("tdd-db", 5432);


    @Value("${container.port}") int composePort;


    @AfterAll
    static void afterAll() {

    }


    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void beforeEach(){
        System.out.println("============= BeforeEach =============");
        System.out.println("compose.port="+composePort);
    }


    @Order(1)
    @Test
    void createAccount() throws ConflictException {
        AccountService service =  new AccountService(accountRepository, accountListener);

        //Account result = accountRepository.save(account);
        Account result = service.create("aa", "aa123");


        Assertions.assertEquals("aa", result.getName(), "가입 요청한 이름과 같아야 한다.");

        assertNotNull(result);
    }


    @Order(2)
    @Test
    void getAccount() throws NotFoundException {
        AccountService service =  new AccountService(accountRepository, accountListener);


        //NotFoundException aThrows= assertThrows(NotFoundException.class, () -> service.getAccount("aa"));
        Account account = service.getAccount("aa");


         assertEquals("aa123", account.getPassword());
    }


    static class ComposePropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("container.port="+composeContainer.getServicePort("tdd-db", 5432))
                    .applyTo(applicationContext.getEnvironment());
        }
    }

}
