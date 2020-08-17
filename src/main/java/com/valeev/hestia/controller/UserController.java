package com.valeev.hestia.controller;

import com.valeev.hestia.dto.AddressDto;
import com.valeev.hestia.dto.UserDto;
import com.valeev.hestia.dto.UserRegisterDto;
import com.valeev.hestia.model.User;
import com.valeev.hestia.security.UserPrincipal;
import com.valeev.hestia.service.UserService;
import com.valeev.hestia.utils.SecurityUtils;
import com.valeev.hestia.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserDto> user() {
        log.info("Получение UserDto");
        UserPrincipal userDetails = SecurityUtils.getUserPrincipal();
        if (userDetails == null) {
            return ResponseEntity.notFound().build();
        }
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        User user = userService.saveUser(userMapper.toUser(userRegisterDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/address")
    public ResponseEntity<Void> addAddressToUser(@Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok().build();
    }

}
