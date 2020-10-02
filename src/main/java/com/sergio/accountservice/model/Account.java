package com.sergio.accountservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Currency;


/*
    We can consider to use apache Lombok, just skipping it for such small project https://projectlombok.org/
 */
public abstract class Account {
    String name;
    Currency currency;
    Money balance;
    final Boolean treasury;

    @JsonCreator
    protected Account(String name, Currency currency, Money balance, Boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Boolean getTreasury() {
        return treasury;
    }
}
