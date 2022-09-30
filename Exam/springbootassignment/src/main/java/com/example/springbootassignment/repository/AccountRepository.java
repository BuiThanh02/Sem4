package com.example.springbootassignment.repository;

import com.example.springbootassignment.entity.Account;
import com.example.springbootassignment.entity.Role;
import com.example.springbootassignment.entity.myenum.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUsernameAndStatus(String username, AccountStatus status);

    Optional<Account> findByEmailAndStatus(String email, AccountStatus status);

    Optional<Account> findByIdAndStatus(String id, AccountStatus status);
    Page<Account> findAll(Pageable pageable);

    Optional<Account> findById(String id);
}
