package com.valeev.hestia.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String login;
    private String addressId;
    private Set<String> roles;
}
