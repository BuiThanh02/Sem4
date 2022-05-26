package com.example.registerauthen.Model;

import com.example.registerauthen.Entity.Account;

import java.util.List;

public interface AccountModel {
    Account save(Account obj); // lưu thông tin.

    List<Account> findAll();

    Account findById(int id);

    Account findByUsername(String username);

    Account findByEmail(String email);

    Account update(int id, Account updateObj);

    boolean delete(int id);
}
