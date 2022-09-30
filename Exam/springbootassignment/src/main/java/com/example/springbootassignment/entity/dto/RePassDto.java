package com.example.springbootassignment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RePassDto {
    @NotEmpty(message = "Password missing")
    private String oldPass;
    @NotEmpty(message = "Password missing")
    private String newPass;
    @NotEmpty(message = "Password missing")
    private String reNewPass;
}
