package com.example.springbootassignment.api;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springbootassignment.entity.Account;
import com.example.springbootassignment.entity.Product;
import com.example.springbootassignment.entity.Role;
import com.example.springbootassignment.entity.dto.AccountDto;
import com.example.springbootassignment.entity.dto.CredentialDto;
import com.example.springbootassignment.entity.dto.LoginDto;
import com.example.springbootassignment.entity.dto.RePassDto;
import com.example.springbootassignment.service.AccountService;
import com.example.springbootassignment.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path = "api/v1/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccountApi {

    final AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AccountDto accountDto) {
        // co the tien hanh validate
        if (!accountDto.getPassword().equals(accountDto.getRePass())){
            return ResponseEntity.badRequest().body("password not match");
        }
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

    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    public ResponseEntity<Object> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("require token in header");
        }
        try {
            String token = authorizationHeader.replace("Bearer", "").trim();
            DecodedJWT decodedJWT = JWTUtil.getDecodedJwt(token);
            String username = decodedJWT.getSubject();
            //load account in the token
            Optional<Account> accountOptional = accountService.getAccount(username);
            Account account = accountOptional.get();
            if (account == null) {
                return ResponseEntity.badRequest().body("Wrong token: Username not exist");
            }
            //now return new token
            //generate tokens
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<String> roles = new ArrayList<>();
            for (Role role:
                    account.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
                roles.add(role.getName());
            }

//            authorities.add(new SimpleGrantedAuthority(account.getRole().getName()));
            String accessToken = JWTUtil.generateToken(
                    account.getUsername(),
                    roles,
                    request.getRequestURL().toString(),
                    JWTUtil.ONE_DAY * 7);

            String refreshToken = JWTUtil.generateToken(
                    account.getUsername(),
                    null,
                    request.getRequestURL().toString(),
                    JWTUtil.ONE_DAY * 14);
            CredentialDto credential = new CredentialDto(accessToken, refreshToken);
            return ResponseEntity.ok(credential);
        } catch (Exception ex) {
            //show error
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/repass/{username}")
    public ResponseEntity<?> rePass(@PathVariable String username, @RequestBody RePassDto rePassDto){
        Optional<Account> accountOptional = accountService.getAccount(username);
        Account account = accountOptional.get();
        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account not found");
        }
        if (!Objects.equals(rePassDto.getNewPass(), rePassDto.getReNewPass())){
            return ResponseEntity.badRequest().body("password not match");
        }
        if (accountService.matchPassword(rePassDto.getOldPass(), account.getPassword())){

            account.setPassword(accountService.encode(rePassDto.getNewPass()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("repass fails");
    }
}
