package com.example.springbootassignment.api.user;

import com.example.springbootassignment.entity.Account;
import com.example.springbootassignment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserAccountApi {
    final AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, path = "/detail/{username}")
    public ResponseEntity<Account> getByUsername(@PathVariable String username){
        Optional<Account> optionalAccount = accountService.getAccount(username);
        if (!optionalAccount.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalAccount.get());
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/detail/{id}")
//    public ResponseEntity<Account> getById(@PathVariable String id){
//        Optional<Account> optionalAccount = Optional.ofNullable(accountService.getAccountById(id));
//        if (!optionalAccount.isPresent()){
//            ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(optionalAccount.get());
//    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
    public ResponseEntity<Account> update(@PathVariable String id, @RequestBody Account updateAccount){
        Optional<Account> optionalAccount = accountService.getAccountById(id);
        if (!optionalAccount.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Account existAccount = optionalAccount.get();

        existAccount.setUpdateAt(LocalDateTime.now());
        existAccount.setPhone(updateAccount.getPhone());
        existAccount.setThumbnail(updateAccount.getThumbnail());
        existAccount.setFullName(updateAccount.getFullName());
        existAccount.setEmail(updateAccount.getEmail());
        existAccount.setAddress(updateAccount.getAddress());

        return ResponseEntity.ok(accountService.save(existAccount));
    }
}
