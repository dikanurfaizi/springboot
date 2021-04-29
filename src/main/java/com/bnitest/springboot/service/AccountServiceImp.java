package com.bnitest.springboot.service;


import com.bnitest.springboot.model.Account;
import com.bnitest.springboot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }

    @Override
    public void saveAccount(Account account){
        this.accountRepository.save(account);
    }

    @Override
    public Account getAccountById(int id){
        Optional<Account> optional = accountRepository.findById(id);
        Account account = null;
        if(optional.isPresent()){
            account = optional.get();
        }else{
            throw new RuntimeException(" Account not found for Account Number :: " + id);
        }
        return account;
    }

    @Override
    public void deleteAccountById(int id){
        this.accountRepository.deleteById(id);
    }
}
