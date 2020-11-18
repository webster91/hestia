package com.valeev.hestia.controller;

import com.valeev.hestia.dto.JwtDto;
import com.valeev.hestia.dto.UserLoginDto;
import com.valeev.hestia.model.User;
import com.valeev.hestia.security.JwtProvider;
import com.valeev.hestia.security.Role;
import com.valeev.hestia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody UserLoginDto loginRq) {
        String telephone = loginRq.getTelephone();
        User user = userService.findByTelephone(telephone);
        if (user != null) {
            Set<Role> authorities = user.getRoles();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(telephone, loginRq.getPassword(), authorities));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateJwtToken(authentication, loginRq.isRememberMe());
            return ResponseEntity.ok(new JwtDto(jwt, telephone, authorities));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

}
