package com.example.tdd.controller;

import com.example.tdd.controller.exception.NotFoundException;
import com.example.tdd.data.AccountRepository;
import com.example.tdd.data.entity.Account;
import com.example.tdd.data.entity.AccountListener;
import com.example.tdd.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * @Mock 어노테이션 사용을 위해서는 {@link ExtendWith @ExtendWith(MockitoExtension.class)} 으로 확장 기능 등록 필요.
 *
 *
 * @see <a href="https://site.mockito.org/">Mockito</a><br>
 *
 */
@ExtendWith(MockitoExtension.class)
public class MockitoServiceTest {

    /**
     * 어노테이션을 이용한 전역으로 Mock 사용 방법
     */
    @Mock
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountListener accountListener;

    /**
     * 집적 Mock 함수를 이용한 Mocking
     */
    @Test
    void createNativieMocking01(){
        AccountService service = mock(AccountService.class);
        AccountRepository repository = mock(AccountRepository.class);

        System.out.println("Globel AccountService");
        assertNotNull(service);
        assertNotNull(repository);
    }


    /**
     *  @Mock을 사용하여 개발 테스트에서만 사용하는 경우
     *
     * @param accountService servie mock
     * @param accountRepository reposixtory mock
     */
    //@Test
    void createNativieMocking02(@Mock AccountService accountService, @Mock AccountRepository accountRepository){
        AccountService service = accountService;
        AccountRepository repository = accountRepository;
        
        assertNotNull(service);
        assertNotNull(repository);

    }

    /**
     * Mock obj stubbing 01
     */
    @Test
    void accountStubbing01() throws NotFoundException {

        System.out.println("AccountService: "+ accountService.toString());
        System.out.println("AccountRepository: "+ accountRepository.toString());


        Account account = new Account();
        account.setName("userName");
        account.setPassword("Password");

        // 예상 상황 정의
        when(accountService.getAccount("userName")).thenReturn(account);
        
        Account result = accountService.getAccount("userName");
        assertEquals("userName", result.getName(), "조회한 사용자 정보가 나와야 한다.");


        // 예외 상환 정의
        when(accountService.getAccount("unkown")).thenThrow(NotFoundException.builder().build());

        assertThrows(NotFoundException.class, () -> {
           accountService.getAccount("unkown");
        }, "NotFoundExceptin을 발생한다");


        //실행 순서에 따른 상황 정의
        when(accountService.getAccount(any()))
                .thenReturn(account)
                .thenThrow(NotFoundException.builder().build())
                .thenReturn(new Account());

        result = accountService.getAccount("userName");
        assertEquals("userName", result.getName());

        assertThrows(NotFoundException.class, () -> {
            accountService.getAccount("userName");
        }, "NotFoundExceptin을 발생한다");

        result = accountService.getAccount("userName");
        assertNotNull(result, "기본 초기화 값은 Null 이어야 한다.");

    }




    @Test
    void accountStubbing02() throws NotFoundException {
        System.out.println("AccountService: "+ accountService.toString());
        System.out.println("AccountRepository: "+ accountRepository.toString());

        // TODO: 2022-06-03 Service 객체에서 getAccount 메소드를 'userName' 값으로 호출하면 Acccount 객체를 리턴하도록 Stubbing

        Account account = Account.builder()
                .name("userName")
                .password("password").build();

        when(accountService.getAccount("userName")).thenReturn(account);

        Account result = accountService.getAccount("userName");

        // TODO: 2022-06-03 AccountRepository 객체에 save 메소드를 account객체를 넣어 호출하면 account 객체가 그대로 리턴되돌고 Stubbing

        when(accountRepository.save(account)).thenReturn(account);

        assertNotNull(accountRepository.save(account), "AccountRepository.save(account) 호출시 Null 반환이어서는 안된다.");

    }


    @Test
    void verifyObJ() throws NotFoundException {
        AccountService service = new AccountService(accountRepository, accountListener);
        assertNotNull(service);

        Account account = Account.builder()
                .name("userName")
                .password("password").build();

        when(accountRepository.findByName("userName")).thenReturn(Optional.of(account));

        Account result = service.getAccount("userName");

        verify(accountListener, times(1)).notify(account);
        verify(accountListener, timeout(100).times(1)).validate(null);

        InOrder inOrder =  inOrder(accountListener);

        inOrder.verify(accountListener).notify(account);
        //verifyNoMoreInteractions(accountListener);  // 더이상 인터렉션이 있으면 안됨
        inOrder.verify(accountListener).validate(null);

    }

    @Test
    void BDDtest() throws NotFoundException {

        //Given
        AccountService service = new AccountService(accountRepository, accountListener);
        assertNotNull(service);

        Account account = Account.builder()
                .name("userName")
                .password("password").build();

        //when(accountRepository.findByName("userName")).thenReturn(Optional.of(account));
        given(accountRepository.findByName("userName")).willReturn(Optional.of(account)); // Mockito BDD Model 예

        //When
        Account result = service.getAccount("userName");


        //Then
        //verify(accountListener, times(1)).notify(account);
        //verify(accountListener, timeout(100).times(1)).validate(null);
        then(accountListener).should(times(1)).notify(account);
        then(accountListener).should(timeout(100).times(1)).validate(null);

        InOrder inOrder =  inOrder(accountListener);

        inOrder.verify(accountListener).notify(account);
        //verifyNoMoreInteractions(accountListener);  // 더이상 인터렉션이 있으면 안됨
        inOrder.verify(accountListener).validate(null);

    }

}
