package com.sergio.accountservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private final String sourceId;
    private final String destId;

    private final Integer amount;

    public Transaction(String sourceId, String destId, Integer amount) {
        this.sourceId = sourceId;
        this.destId = destId;
        this.amount = amount;
    }

    private Transaction() {
        this(null, null, null);
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getDestId() {
        return destId;
    }

    public Integer getAmount() {
        return amount;
    }
}
