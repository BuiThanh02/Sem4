package com.example.springsecuritydemo.restapi;

import com.example.springsecuritydemo.entity.Account;
import com.example.springsecuritydemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserApi {
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Account>> findAll(){
        return ResponseEntity.ok(accountRepository.findAll());
    }

}
