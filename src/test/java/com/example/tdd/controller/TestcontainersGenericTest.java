package com.example.tdd.controller;

import com.example.tdd.controller.exception.ConflictException;
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
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@Slf4j
@SpringBootTest
@ActiveProfiles("Testcontainers_generic")
@Testcontainers
@ContextConfiguration(initializers = TestcontainersGenericTest.ContainerPropertyInitializer.class)
class TestcontainersGenericTest {

    @Mock
    AccountListener accountListener;

    @Autowired
    AccountRepository accountRepository;

    @Value("${container.port}") int port;

    @Container
    static GenericContainer postgreSQLContainer = new GenericContainer("postgres")
            .withExposedPorts(5432)
            .waitingFor(Wait.forListeningPort())
            .withEnv(Map.of("POSTGRES_DB", "TDD"
                    , "POSTGRES_USER", "test"
                    ,"POSTGRES_PASSWORD", "test"));



    @BeforeAll
    static void beforeAll() {
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
        postgreSQLContainer.followOutput(logConsumer);

        postgreSQLContainer.start();

        System.out.println("==========================");
        System.out.println(postgreSQLContainer.getMappedPort(5432));

    }

    @BeforeEach
    void beforeEach(){
        System.out.println("============= BeforeEach =============");
        System.out.println("port="+port);

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



    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("container.port="+postgreSQLContainer.getMappedPort(5432))
                    .applyTo(applicationContext);
        }
    }
}
