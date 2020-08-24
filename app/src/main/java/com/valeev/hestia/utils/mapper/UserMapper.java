package com.valeev.hestia.utils.mapper;

import com.valeev.hestia.dto.UserDto;
import com.valeev.hestia.dto.UserRegisterDto;
import com.valeev.hestia.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "username", target = "username")
    User toUser(UserRegisterDto userRegisterDto);
}
