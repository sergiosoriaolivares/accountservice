package com.sergio.accountservice.controller;

import com.sergio.accountservice.model.Account;
import com.sergio.accountservice.model.Money;
import com.sergio.accountservice.model.Transaction;
import com.sergio.accountservice.model.TreasuryAccount;
import com.sergio.accountservice.repository.AccountRepository;
import com.sergio.accountservice.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {
    public static final String BASE_PATH = "/transactions";
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void prepareData() {
        Account firstAccount = TreasuryAccount.builder()
                .withName("firstAccount")
                .withBalance(Money.builder().withAmount(10).build())
                .withCurrency(Currency.getInstance("EUR"))
                .build();

        Account secondAccount = TreasuryAccount.builder()
                .withName("secondAccount")
                .withBalance(Money.builder().withAmount(-5).build())
                .withCurrency(Currency.getInstance("EUR"))
                .build();

        Account thirdAccount = TreasuryAccount.builder()
                .withName("thirdAccount")
                .withBalance(Money.builder().withAmount(90).build())
                .withCurrency(Currency.getInstance("EUR"))
                .build();

        accountRepository.save(firstAccount);
        accountRepository.save(secondAccount);
        accountRepository.save(thirdAccount);
    }

    @Test
    public void shouldDoATransaction() {
        Transaction transaction = new Transaction("firstAccount", "thirdAccount", 5);

        Transaction result = this.restTemplate.postForObject("http://localhost:" + port + BASE_PATH, transaction, Transaction.class);

        assertThat(result).isNotNull();

        List transactions = this.restTemplate.getForObject("http://localhost:" + port + BASE_PATH, List.class);

        assertThat(transactions).hasSize(1);
    }

    @Test
    public void shouldDoAnInvalidTransaction() {
        Transaction transaction = new Transaction("thirdAccount", "first", 5000);

        String result = this.restTemplate.postForObject("http://localhost:" + port + BASE_PATH, transaction, String.class);

        assertThat(result).contains("Bad Request");

        List transactions = this.restTemplate.getForObject("http://localhost:" + port + BASE_PATH, List.class);

        assertThat(transactions).isEmpty();
    }

}
