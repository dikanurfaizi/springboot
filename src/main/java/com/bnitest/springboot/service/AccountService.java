package com.bnitest.springboot.service;

import com.bnitest.springboot.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccount();
    void saveAccount(Account account);
    Account getAccountById(int accNum);
    void deleteAccountById(int accNum);
}
