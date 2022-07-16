package com.example.springboot_assignment.entity.dto;

import com.example.springboot_assignment.entity.myenum.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String thumbnail;
    private String detail;
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus status;
    private String role;
}
