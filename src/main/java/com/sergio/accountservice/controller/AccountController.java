package com.sergio.accountservice.controller;

import com.sergio.accountservice.model.Account;
import com.sergio.accountservice.model.Money;
import com.sergio.accountservice.model.TreasuryAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@RestController
public class AccountController {

    @GetMapping("/accounts")
    public List<Account> getAccount(){
        Account account = buildMockAccount();

        return Arrays.asList(account);
    }

    private Account buildMockAccount() {
        return new TreasuryAccount("Mock Account", Currency.getInstance("EUR"), Money.builder().withAmount(100).build());
    }
}
