package com.sergio.accountservice.model;

import java.util.Currency;

public class NonTreasuryAccount extends Account {
    public NonTreasuryAccount(String name, Currency currency, Money balance) {
        super(name, currency, balance, false);
    }
}
