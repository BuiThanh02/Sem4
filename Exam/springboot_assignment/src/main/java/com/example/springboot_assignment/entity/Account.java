package com.example.springboot_assignment.entity;

import com.example.springboot_assignment.entity.base.BaseEntity;
import com.example.springboot_assignment.entity.myenum.AccountStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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
