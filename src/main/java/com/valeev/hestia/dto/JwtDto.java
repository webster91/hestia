package com.valeev.hestia.dto;

import com.valeev.hestia.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class JwtDto {
    String accessToken;
    String username;
    Set<Role> authorities;
}
