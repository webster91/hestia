package com.valeev.hestia.controller;

import com.valeev.hestia.dto.AddressLinkDto;
import com.valeev.hestia.dto.UserDto;
import com.valeev.hestia.dto.UserRegisterDto;
import com.valeev.hestia.security.UserPrincipal;
import com.valeev.hestia.service.UserService;
import com.valeev.hestia.utils.SecurityUtils;
import com.valeev.hestia.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

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
        userDto.setAddressId(userDetails.getAddressId());
        userDto.setRoles(AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        userService.saveUser(userMapper.toUser(userRegisterDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/address")
    @PreAuthorize("hasAuthority(T(com.valeev.hestia.security.Role).ADMIN)")
    public ResponseEntity<Void> addAddressToUser(@Valid @RequestBody AddressLinkDto addressDto) {
        userService.linkAddressByTelephone(addressDto.getAddressId(), addressDto.getTelephone());
        return ResponseEntity.ok().build();
    }

}
