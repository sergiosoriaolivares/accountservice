package com.sergio.accountservice.controller;

import com.sergio.accountservice.model.Transaction;
import com.sergio.accountservice.repository.TransactionRepository;
import com.sergio.accountservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private TransactionRepository repository;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(){
        List<Transaction> transactions = repository.findAll();

        return transactions;
    }

    @PostMapping("/transactions")
    Transaction newAccount(@RequestBody Transaction newTransaction) {
        return service.validateAndSave(newTransaction);
    }
}
