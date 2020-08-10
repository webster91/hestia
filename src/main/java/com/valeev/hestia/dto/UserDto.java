package com.valeev.hestia.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {
    private String id;
    private String name;
    private String login;
    private Set<String> roles;
}
