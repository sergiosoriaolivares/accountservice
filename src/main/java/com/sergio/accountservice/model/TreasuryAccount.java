package com.sergio.accountservice.model;

import javax.persistence.Entity;
import java.util.Currency;

@Entity
public class TreasuryAccount extends Account {
    private TreasuryAccount() {
        // Default constructor for Hibernate
        this(null, null, null);
    }

    public TreasuryAccount(String name, Currency currency, Money balance) {
        super(name, currency, balance, true);
    }

    public static TreasuryAccount.TreasuryAccountBuilder builder() {
        return new TreasuryAccount.TreasuryAccountBuilder();
    }

    public static class TreasuryAccountBuilder extends AccountBuilder {

        public TreasuryAccount build() {
            return new TreasuryAccount(name, currency, balance);
        }
    }
}
