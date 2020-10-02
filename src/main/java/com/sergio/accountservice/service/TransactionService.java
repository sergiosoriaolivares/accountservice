package com.sergio.accountservice.service;

import com.sergio.accountservice.exception.TransactionNotAllowedException;
import com.sergio.accountservice.model.Account;
import com.sergio.accountservice.model.Transaction;
import com.sergio.accountservice.repository.AccountRepository;
import com.sergio.accountservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction validateAndSave(Transaction transaction) {
        Optional<Account> sourceAccount = accountRepository.findById(transaction.getSourceId());
        Optional<Account> destAccount = accountRepository.findById(transaction.getDestId());

        Account source = sourceAccount.filter(account -> account.allowTransaction(transaction.getAmount())).orElseThrow(TransactionNotAllowedException::new);
        Account dest = destAccount.orElseThrow(TransactionNotAllowedException::new);

        source.getBalance().setAmount(source.getBalance().getAmount() - transaction.getAmount());
        dest.getBalance().setAmount(source.getBalance().getAmount() + transaction.getAmount());

        return persist(transaction, source, dest);
    }

    @Transactional
    public Transaction persist(Transaction transaction, Account source, Account dest) {
        accountRepository.save(source);
        accountRepository.save(dest);
        return transactionRepository.save(transaction);
    }
}
