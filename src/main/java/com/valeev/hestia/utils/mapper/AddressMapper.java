package com.valeev.hestia.utils.mapper;

import com.valeev.hestia.dto.AddressDto;
import com.valeev.hestia.model.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressMapper {

    AddressDto toAddressDto(Address address);

    @Mapping(target = "receipts", ignore = true)
    Address toAddress(AddressDto addressDto);
}
