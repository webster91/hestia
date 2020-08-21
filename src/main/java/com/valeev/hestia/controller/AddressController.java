package com.valeev.hestia.controller;

import com.valeev.hestia.dto.AddressDto;
import com.valeev.hestia.dto.ReceiptDto;
import com.valeev.hestia.model.Address;
import com.valeev.hestia.service.AddressService;
import com.valeev.hestia.utils.mapper.AddressMapper;
import com.valeev.hestia.utils.mapper.ReceiptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final ReceiptMapper receiptMapper;

    @GetMapping("/address")
    @PreAuthorize("hasRole(T(com.valeev.hestia.security.Role).ADMIN)")
    public ResponseEntity<List<AddressDto>> getAddresses() {
        List<AddressDto> address = addressService.findAll().stream()
                .map(addressMapper::toAddressDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("addressId") String addressId) {
        Address address = addressService.findById(addressId);
        return ResponseEntity.ok(addressMapper.toAddressDto(address));
    }


    @GetMapping("/address/house/{house}")
    public ResponseEntity<List<AddressDto>> getAddressByHouse(@PathVariable String house) {
        List<AddressDto> address = addressService.findByHouse(house).stream()
                .map(addressMapper::toAddressDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/street/{street}")
    public ResponseEntity<List<AddressDto>> getAddressByStreet(@PathVariable String street) {
        List<AddressDto> address = addressService.findByStreet(street).stream()
                .map(addressMapper::toAddressDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/city/{city}")
    public ResponseEntity<List<AddressDto>> getAddressByCity(@PathVariable String city) {
        List<AddressDto> address = addressService.findByCity(city).stream()
                .map(addressMapper::toAddressDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/{addressId}/receipt")
    public ResponseEntity<List<ReceiptDto>> getReceiptsByAddressId(@PathVariable("addressId") String addressId) {
        List<ReceiptDto> receipts = Optional.ofNullable(addressService.findById(addressId))
                .map(Address::getReceipts)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(receiptMapper::toReceiptDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(receipts);
    }

}
