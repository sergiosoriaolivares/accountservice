package com.sergio.accountservice.controller;

import com.sergio.accountservice.exception.AccountNotFoundException;
import com.sergio.accountservice.model.Account;
import com.sergio.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @GetMapping("/accounts")
    public List<Account> getAccount(){
        List<Account> accounts = repository.findAll();

        return accounts;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    @GetMapping("/accounts/{name}")
    Account findOne(@PathVariable String name) {
        return repository.findById(name)
                .orElseThrow(() -> new AccountNotFoundException(name));
    }

    @PutMapping("/accounts/{name}")
    Account saveOrUpdate(@RequestBody Account newAccount, @PathVariable String name) {

        return repository.findById(name)
                .map(x -> {
                    x.update(newAccount);

                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newAccount.setName(name);
                    return repository.save(newAccount);
                });
    }

    @DeleteMapping("/accounts/{name}")
    void deleteAccount(@PathVariable String name) {
        repository.deleteById(name);
    }

}
