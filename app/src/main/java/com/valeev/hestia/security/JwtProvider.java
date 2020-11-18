package com.valeev.hestia.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    private Key key;

    @Value("${app.jwtExpiration}")
    private Integer jwtExpiration;

    @Value("${app.jwtExpiration}")
    private Integer jwtExpirationWithRememberMe;

    public String generateJwtToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + jwtExpirationWithRememberMe);
        } else {
            validity = new Date(now + jwtExpiration);
        }
        UserPrincipal details = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(details.getUser().getTelephone())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
