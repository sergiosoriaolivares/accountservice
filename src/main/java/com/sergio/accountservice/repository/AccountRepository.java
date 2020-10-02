package com.sergio.accountservice.repository;

import com.sergio.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
