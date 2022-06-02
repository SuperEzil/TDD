package com.example.tdd.service;

import com.example.tdd.controller.exception.ConflictException;
import com.example.tdd.controller.exception.NotFoundException;
import com.example.tdd.controller.exception.NotModifiedException;
import com.example.tdd.data.UserInfo;
import com.example.tdd.data.AccountRepository;
import com.example.tdd.data.entity.Account;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * 사용자 가입 서비스
 */
@Log4j2
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * 가입
     * @param name 이름
     * @param password 비밀 번호
     * @return 가입 정보 반환
     * @throws ConflictException 중복 가입 예외
     */
    public Account create(String name, String password) throws ConflictException {
        Account account = null;
        try {
            account = findAccountByName(name);
        } catch (NotFoundException e) {
            log.info(e.getMessage());
        }

        if (account != null)
            throw ConflictException.builder()
                    .message("이전 가입 정보가 있습니다.").build();


        return saveAccount(Account.builder()
                .name(name)
                .password(password)
                .build());
    }


    /**
     * 가입 정보 찾기
     * @param iD database Pk id
     * @return 조회 정보
     * @throws NotFoundException 정보를 찾을수 없는 경우 예외
     */
    public Account getAccount(Integer iD) throws NotFoundException {
        return findAccountById(iD);
    }

    /**
     * 가입 정보 변경
     * @param iD database Pk id
     * @param info 변경 요청 사용자 정보
     * @return 변경된 정보 반환
     * @throws NotFoundException 정보를 찾을수 없는 경우 예외
     * @throws NotModifiedException 정보 변경이 불가능한 경우 에외
     */
    public Account updateAccount(Integer iD, UserInfo info) throws NotFoundException, NotModifiedException {
        Account account = findAccountById(iD);

        account.setName(info.getName());
        account.setPassword(info.getPassword());

        Account result = saveAccount(account);

        if (result == null)
            throw NotModifiedException.builder().build();

        return result;
    }

    /**
     * 사용자 삭제
     * @param id iD database Pk id
     * @return 삭제 가입자 정보
     * @throws NotFoundException 정보를 찾을수 없는 경우 예외
     */
    public Account deleteAccount(Integer id) throws NotFoundException {
        Account account = findAccountById(id);
        deleteAccountById(account);
        return account;
    }



    ////////////////// userRepository Func


    protected Account saveAccount(Account account)  {
            return accountRepository.save(account);
    }

    protected Account findAccountById(@NotNull Integer _Id) throws NotFoundException {
        Optional<Account> data = accountRepository.findById(_Id);

        if (data.isPresent())
            return data.get();

        throw NotFoundException.builder()
                .message("가입 정보를 찾을 수 없습니다.")
                .build();
    }

    protected Account findAccountByName(@NotNull String name) throws NotFoundException {
        Optional<Account> data = accountRepository.findByName(name);

        if (data.isPresent())
            return data.get();

        throw NotFoundException.builder()
                .message("가입 정보를 찾을 수 없습니다.")
                .build();
    }

    protected void deleteAccountById(@NotNull Account account){
        accountRepository.delete(account);
    }



}
