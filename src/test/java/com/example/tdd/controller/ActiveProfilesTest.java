
package com.example.tdd.controller;

import com.example.tdd.controller.exception.ConflictException;
import com.example.tdd.data.AccountRepository;
import com.example.tdd.data.entity.Account;
import com.example.tdd.data.entity.AccountListener;
import com.example.tdd.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * 테스트 대상 프로필 지정 및 H2 DB로 대체
 */
@SpringBootTest
@ActiveProfiles("h2")
public class ActiveProfilesTest {

    @Mock
    AccountListener accountListener;

    @Autowired
    AccountRepository accountRepository;

    @Test
    @Transactional
    void createAccount() throws ConflictException {

        AccountService service =  new AccountService(accountRepository, accountListener);

        Account account = Account.builder()
                .name("use1")
                .password("pass1").build();

        //Account result = accountRepository.save(account);
        Account result = service.create("aa", "aa123");

        assertNotNull(result);

        assertEquals("aa", result.getName(), "가입 요청한 이름과 같아야 한다.");
    }

}
