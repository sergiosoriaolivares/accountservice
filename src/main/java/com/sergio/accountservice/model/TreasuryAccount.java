package com.sergio.accountservice.model;

import java.util.Currency;

public class TreasuryAccount extends Account {
    public TreasuryAccount(String name, Currency currency, Money balance) {
        super(name, currency, balance, true);
    }
}
