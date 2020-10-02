package com.sergio.accountservice.controller;

import com.sergio.accountservice.model.Account;
import com.sergio.accountservice.model.Money;
import com.sergio.accountservice.model.TreasuryAccount;
import com.sergio.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
    public static final String BASE_PATH = "/accounts";
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountRepository accountRepository;

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
    public void shouldReturnAllElements() {
        List accounts = this.restTemplate.getForObject("http://localhost:" + port + BASE_PATH, List.class);

        assertThat(accounts).hasSize(3);
    }

    @Test
    public void shouldFindOne() {
        String responsebody = this.restTemplate.getForObject("http://localhost:" + port + BASE_PATH + "/secondAccount", String.class);

        assertThat(responsebody).isNotNull();
        assertThat(responsebody).contains("secondAccount");

    }

    @Test
    @Disabled("FIXME: There is a problem on jackson configuration, that casue an issue when deserializing Abstract class. I was reading and the solution is clear, but there is something wrong in the code")
    public void shouldUpdateOne() {
        Account account = TreasuryAccount.builder()
                .withName("thirdAccount")
                .withBalance(Money.builder().withAmount(500).build())
                .withCurrency(Currency.getInstance("EUR"))
                .build();

        this.restTemplate.put("http://localhost:" + port + BASE_PATH + "/thirdAccount", account);
        String responseBody = this.restTemplate.getForObject("http://localhost:" + port + BASE_PATH + "/thirdAccount", String.class);

        assertThat(responseBody).contains("\"amount\": -500");
    }

    @Test
    public void shouldDeleteOne() {

        this.restTemplate.delete("http://localhost:" + port + BASE_PATH + "/secondAccount");
        String responseBody = this.restTemplate.getForObject("http://localhost:" + port + BASE_PATH + "/secondAccount", String.class);

        assertThat(responseBody).contains("Not Found");
    }

    private Account buildMockAccount() {
        return new TreasuryAccount("Mock Account", Currency.getInstance("EUR"), Money.builder().withAmount(100).build());
    }
}
