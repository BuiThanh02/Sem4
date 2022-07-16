package com.example.springsecuritydemo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialDto {
    private String accessToken;
    private String refreshToken;
}
