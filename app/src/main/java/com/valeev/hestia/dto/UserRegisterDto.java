package com.valeev.hestia.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDto {
    private String username;
    private String email;
    private String telephone;
    private String password;
}
