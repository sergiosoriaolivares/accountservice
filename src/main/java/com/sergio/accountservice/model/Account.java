package com.sergio.accountservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Currency;


/*
    We can consider to use apache Lombok, just skipping it for such small project https://projectlombok.org/
 */
@Entity
public abstract class Account {
    @Id
    String name;
    Currency currency;
    @OneToOne (cascade= CascadeType.ALL)
    Money balance;
    final Boolean treasury;

    @JsonCreator
    protected Account(String name, Currency currency, Money balance, Boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }

    public void update(Account newAccount){
        this.name = newAccount.name;
        this.currency = newAccount.currency;
        this.balance = newAccount.balance;
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

    public abstract static class AccountBuilder {
        String name;
        Currency currency;
        Money balance;

        public AccountBuilder withName(String name) {
            this.name = name;

            return this;
        }

        public AccountBuilder withCurrency(Currency currency) {
            this.currency = currency;

            return this;
        }

        public AccountBuilder withBalance(Money balance) {
            this.balance = balance;

            return this;
        }

        public abstract Account build();
    }
}
