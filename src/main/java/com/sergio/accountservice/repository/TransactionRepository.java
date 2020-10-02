package com.sergio.accountservice.repository;

import com.sergio.accountservice.model.Account;
import com.sergio.accountservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
