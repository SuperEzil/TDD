package com.example.tdd.data;

import com.example.tdd.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * 사용자 DB 레파스토리지
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByName(String name);



}