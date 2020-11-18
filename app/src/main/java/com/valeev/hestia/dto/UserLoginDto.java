package com.valeev.hestia.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserLoginDto {

    @NotNull
    @Size(min = 1, max = 50)
    private String telephone;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    private boolean rememberMe;

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "telephone='" + telephone + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
