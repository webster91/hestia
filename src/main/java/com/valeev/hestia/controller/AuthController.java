package com.valeev.hestia.controller;

import com.valeev.hestia.dto.JwtDto;
import com.valeev.hestia.dto.UserLoginDto;
import com.valeev.hestia.model.User;
import com.valeev.hestia.security.JwtProvider;
import com.valeev.hestia.security.Role;
import com.valeev.hestia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody UserLoginDto loginRequest) {

        User user = userService.findByTelephone(loginRequest.getTelephone());

        if (user != null) {
            val authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getTelephone(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String telephone = user.getTelephone();
            String jwt = jwtProvider.generateJwtToken(telephone);
            Set<Role> authorities = user.getRoles();
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
