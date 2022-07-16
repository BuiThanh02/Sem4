package com.example.springboot_assignment.repository;

import com.example.springboot_assignment.entity.Account;
import com.example.springboot_assignment.entity.myenum.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);

    Page<Account> findAllByStatusEquals(AccountStatus status, Pageable pageable);

    Page<Account> findAllByRoleEquals(String role, Pageable pageable);

    Page<Account> findAllByAddressContaining(String address, Pageable pageable);

    Account findByPhone(String phone);

    Account findByEmail(String email);

    Page<Account> findAllByCreateAt(LocalDateTime createdAt, Pageable pageable);

    Page<Account> findAllByUpdateAt(LocalDateTime updatedAt, Pageable pageable);

    Page<Account> findAllByDeleteAt(LocalDateTime deletedAt, Pageable pageable);
}