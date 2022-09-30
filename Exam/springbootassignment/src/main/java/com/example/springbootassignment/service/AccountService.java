package com.example.springbootassignment.service;

import com.example.springbootassignment.entity.Account;
import com.example.springbootassignment.entity.Role;
import com.example.springbootassignment.entity.dto.AccountDto;
import com.example.springbootassignment.entity.dto.CredentialDto;
import com.example.springbootassignment.entity.myenum.AccountStatus;
import com.example.springbootassignment.repository.AccountRepository;
import com.example.springbootassignment.repository.RoleRepository;
import com.example.springbootassignment.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    final AccountRepository accountRepository;

    final PasswordEncoder passwordEncoder;

    final RoleRepository roleRepository;

    public Account register(AccountDto accountDto){
        Set<Role> roles = new HashSet<>();
//        for (Role role: accountDto.getRoles()) {
//            Optional<Role> userRoleOptional = roleRepository.findByName(role.getName());
//            Role userRole = userRoleOptional.orElse(null);
//            if (userRole == null) {
//                //create new role
//                userRole = roleRepository.save(new Role("user"));
//                return null;
//            }
//            roles.add(userRoleOptional.get());
//        }
        Role role = roleRepository.findByName("user");
        if (role == null){
            role = new Role("user");
        }
        roles.add(role);
        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .address(null)
                .detail(null)
                .email(null)
                .phone(null)
                .fullName(accountDto.getFullName())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .username(accountDto.getUsername())
                .status(AccountStatus.ACTIVE)
                .thumbnail(null)
                .roles(roles)
                .build();
        account.setCreateAt(LocalDateTime.now());
        account.setUpdateAt(LocalDateTime.now());
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsernameAndStatus(username, AccountStatus.ACTIVE);
        Account account = accountOptional.orElse(null);
        if (account == null) {
            throw new UsernameNotFoundException("User not found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:
                account.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

    public boolean matchPassword(String rawPassword, String hashPassword){
        return passwordEncoder.matches(rawPassword, hashPassword);
    }

    public CredentialDto generateCredential(UserDetails userDetail, HttpServletRequest request) {
        String accessToken = JWTUtil.generateToken(userDetail.getUsername(),
                userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                request.getRequestURI(),
                JWTUtil.ONE_DAY * 7);

        String refreshToken = JWTUtil.generateToken(userDetail.getUsername(),
                userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                request.getRequestURI(),
                JWTUtil.ONE_DAY * 14);
        return new CredentialDto(accessToken, refreshToken);
    }

    public Optional <Account> getAccount(String username) {
        return accountRepository.findByUsernameAndStatus(username, AccountStatus.ACTIVE);
    }

    public Optional <Account> getAccountById(String id){
        return accountRepository.findByIdAndStatus(id, AccountStatus.ACTIVE);
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public String encode(String password){
        return passwordEncoder.encode(password);
    }

    public Page<Account> findAll(int page, int limit) {
        return accountRepository.findAll(PageRequest.of(page, limit));
    }

    public Optional<Account> findByEmail(String email){
        return accountRepository.findByEmailAndStatus(email, AccountStatus.ACTIVE);
    }

    public Optional<Account> findById(String id){
        return accountRepository.findById(id);
    }

    public void deleteById(String id) {
        Optional<Account> optionalAccount =  accountRepository.findByIdAndStatus(id, AccountStatus.ACTIVE);
        Account account = optionalAccount.get();
        account.setStatus(AccountStatus.DEACTIVE);
        accountRepository.save(account);
    }
}
