package com.example.springbootassignment.entity.dto;

import com.example.springbootassignment.entity.Account;
import com.example.springbootassignment.entity.Role;
import com.example.springbootassignment.entity.base.BaseEntity;
import com.example.springbootassignment.entity.myenum.AccountStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto {
    @NotEmpty(message = "username missing")
    private String username;
    @NotEmpty(message = "password missing")
    @Min(value = 8, message = "password not strong enough")
    private String password;
    @NotEmpty(message = "password repeat missing")
    private String rePass;
    @NotEmpty(message = "full name missing")
    private String fullName;
}
