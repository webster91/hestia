package com.valeev.hestia.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {

    private String id;

    private String city;

    private String street;

    private String house;

    private String flat;
}
