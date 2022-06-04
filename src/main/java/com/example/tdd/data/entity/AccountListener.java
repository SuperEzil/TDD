package com.example.tdd.data.entity;

public interface AccountListener {
    void notify(Account account);
    void validate(Account account);
}
