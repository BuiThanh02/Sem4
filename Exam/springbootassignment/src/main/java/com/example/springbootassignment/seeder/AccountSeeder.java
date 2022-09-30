package com.example.springbootassignment.seeder;

import com.example.springbootassignment.entity.Account;
import com.example.springbootassignment.entity.Role;
import com.example.springbootassignment.entity.myenum.AccountStatus;
import com.example.springbootassignment.repository.AccountRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AccountSeeder {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    Faker faker = new Faker();

    public static List<Account> accountList = new ArrayList<>();
    public static final int NUMBER_OF_ACCOUNT = 10;

    public void generate(){
        Account account1 = new Account();
        Set<Role> roles1 = new HashSet<>();
        Role role1 = new Role("admin");
        Role role2 = new Role("proAdmin");
        roles1.add(role1);
        roles1.add(role2);
        account1.setId(UUID.randomUUID().toString());
        account1.setAddress(faker.address().fullAddress());
        account1.setRoles(roles1);
        account1.setDetail(faker.lorem().sentence());
        account1.setEmail(faker.internet().emailAddress());
        account1.setFirstName(faker.name().firstName());
        account1.setLastName(faker.name().lastName());
        account1.setUsername("admin");
        account1.setPassword(passwordEncoder.encode("admin"));
        account1.setPhone(faker.phoneNumber().cellPhone());
        account1.setThumbnail(faker.avatar().image());
        account1.setStatus(AccountStatus.ACTIVE);
        accountList.add(account1);
//        for(int i = 0; i < NUMBER_OF_ACCOUNT; i++){
//            Account account = new Account();
//            Set<Role> roles = new HashSet<>();
//            Role role = new Role("user");
//            roles.add(role);
//            account.setId(UUID.randomUUID().toString());
//            account.setAddress(faker.address().fullAddress());
//            account.setRoles(roles);
//            account.setDetail(faker.lorem().sentence());
//            account.setEmail(faker.internet().emailAddress());
//            account.setFirstName(faker.name().firstName());
//            account.setLastName(faker.name().lastName());
//            account.setUsername(faker.leagueOfLegends().champion());
//            account.setPassword(passwordEncoder.encode(faker.artist().name()));
//            account.setPhone(faker.phoneNumber().cellPhone());
//            account.setThumbnail(faker.avatar().image());
//            account.setStatus(AccountStatus.ACTIVE);
//            accountList.add(account);
//        }
        accountRepository.saveAll(accountList);
    }
}
