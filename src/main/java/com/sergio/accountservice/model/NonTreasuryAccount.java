package com.sergio.accountservice.model;

import javax.persistence.Entity;
import java.util.Currency;

@Entity
public class NonTreasuryAccount extends Account {
    private NonTreasuryAccount() {
        // Default constructor for Hibernate
        this(null, null, null);
    }

    public NonTreasuryAccount(String name, Currency currency, Money balance) {
        super(name, currency, balance, false);
    }

    public static NonTreasuryAccountBuilder builder() {
        return new NonTreasuryAccountBuilder();
    }

    public static class NonTreasuryAccountBuilder extends AccountBuilder {

        public NonTreasuryAccount build() {
            return new NonTreasuryAccount(name, currency, balance);
        }
    }
}
