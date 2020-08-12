package com.valeev.hestia.controller;

import com.valeev.hestia.dto.AddressDto;
import com.valeev.hestia.dto.UserDto;
import com.valeev.hestia.security.UserPrincipal;
import com.valeev.hestia.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {

    @GetMapping("/user")
    public ResponseEntity<UserDto> user() {
        log.info("Получение UserDto");
        UserPrincipal userDetails = SecurityUtils.getUserPrincipal();
        UserDto userDto = new UserDto();
        userDto.setId(userDetails.getId());
        userDto.setName(userDetails.getUsername());
        userDto.setLogin(userDetails.getUsername());
        userDto.setRoles(AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/logOut")
    public ResponseEntity<Void> logOut(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user")
    public ResponseEntity<Void> addUser(@Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/address")
    public ResponseEntity<Void> addAddressToUser(@Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok().build();
    }

}
