package com.sergio.accountservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

/*
    This class probably is intended to represent complex
    blockchain data structure, at this stage, I will consider
    simple amount (like a wallet).
    It shall be able to evolve later to more complex model.
 */
@Entity
public class Money {

    @Id
    @GeneratedValue
    private Long id;

    private AtomicInteger amount;

    private Money() {
        // Default constructor for Hibernate
    }

    public Money(AtomicInteger amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount.get();
    }

    public void setAmount(Integer amount) {
        this.amount.set(amount);
    }

    public static MoneyBuilder builder() {
        return new MoneyBuilder();
    }

    public static class MoneyBuilder {
        private Integer amount;

        public MoneyBuilder withAmount(Integer amount) {
            this.amount = amount;

            return this;
        }

        public Money build() {
            return new Money(new AtomicInteger(amount));
        }
    }
}
