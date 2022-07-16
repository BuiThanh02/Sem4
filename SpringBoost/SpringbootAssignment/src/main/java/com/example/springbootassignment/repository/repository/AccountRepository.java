package com.example.springbootassignment.repository.repository;

import com.example.springsecuritydemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountByUserName(String username);
}
