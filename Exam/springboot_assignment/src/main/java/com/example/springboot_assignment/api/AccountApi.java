package com.example.springboot_assignment.api;

import com.example.springboot_assignment.entity.Account;
import com.example.springboot_assignment.entity.dto.AccountDto;
import com.example.springboot_assignment.entity.dto.CredentialDto;
import com.example.springboot_assignment.entity.dto.LoginDto;
import com.example.springboot_assignment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "api/v1/accounts")
@RequiredArgsConstructor
public class AccountApi {

    final AccountService accountService;

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AccountDto accountDto) {
        // co the tien hanh validate
        Account account = accountService.register(accountDto);
        if (account == null) {
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        UserDetails userDetail = accountService.loadUserByUsername(loginDto.getUsername());
        if (userDetail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account not found");
        }
        if (accountService.matchPassword(loginDto.getPassword(), userDetail.getPassword())) {
            CredentialDto credentialDto = accountService.generateCredential(userDetail, request);
            return ResponseEntity.status(HttpStatus.OK).body(credentialDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login fails");
    }
}
