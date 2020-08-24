package com.valeev.hestia.utils;

import com.valeev.hestia.security.UserPrincipal;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {

    public UserPrincipal getUserPrincipal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return (UserPrincipal) authentication.getPrincipal();
        }
        return null;
    }
}
