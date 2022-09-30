package com.example.springbootassignment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CredentialDto {
    private String accessToken;
    private String refreshToken;
}
