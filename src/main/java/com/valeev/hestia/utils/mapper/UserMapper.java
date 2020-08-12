package com.valeev.hestia.utils.mapper;

import com.valeev.hestia.dto.UserDto;
import com.valeev.hestia.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
