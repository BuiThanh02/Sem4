package com.example.springbootassignment.api.admin;

import com.example.springbootassignment.entity.Account;
import com.example.springbootassignment.entity.Product;
import com.example.springbootassignment.entity.myenum.AccountStatus;
import com.example.springbootassignment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/admin/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminAccountApi {
    final AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Account> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "limit", defaultValue = "10") int limit){
        return accountService.findAll(page, limit);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Account> findById(@PathVariable String id){
        Optional<Account> optionalAccount = accountService.findById(id);
        if (!optionalAccount.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalAccount.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Account account){
        if (accountService.getAccount(account.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("username already in use");
        }
        if (accountService.findByEmail(account.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("email already in use");
        }
        account.setId(UUID.randomUUID().toString());
        do {
            account.setId(UUID.randomUUID().toString());
        }while (accountService.findById(account.getId()).isPresent());
        return ResponseEntity.ok(accountService.save(account));
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        if (!accountService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        Optional<Account> optionalAccount = accountService.findById(id);
        Account account = optionalAccount.get();
        if (account.getStatus() != AccountStatus.ACTIVE){
            ResponseEntity.badRequest().build();
        }
        accountService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Account> update(@PathVariable String id, @RequestBody Account updateAccount){
        Optional<Account> optionalAccount = accountService.findById(id);
        if (!optionalAccount.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Account existAccount = optionalAccount.get();

       existAccount.setUpdateAt(LocalDateTime.now());
       existAccount.setStatus(updateAccount.getStatus());
       existAccount.setAddress(updateAccount.getAddress());
       existAccount.setEmail(updateAccount.getEmail());
       existAccount.setPhone(updateAccount.getPhone());
       existAccount.setThumbnail(updateAccount.getThumbnail());
       existAccount.setFullName(updateAccount.getFullName());
       existAccount.setDetail(updateAccount.getDetail());

        return ResponseEntity.ok(accountService.save(existAccount));
    }
}
