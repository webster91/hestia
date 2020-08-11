package com.valeev.hestia.controller;

import com.valeev.hestia.model.Address;
import com.valeev.hestia.model.Receipt;
import com.valeev.hestia.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;


    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAddresses() {
        List<Address> address = addressService.findAll();
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable("addressId") String addressId) {
        Address address = addressService.findById(addressId);
        return ResponseEntity.ok(address);
    }


    @GetMapping("/address/house/{house}")
    public ResponseEntity<List<Address>> getAddressByHouse(@PathVariable String house) {
        List<Address> address = addressService.findByHouse(house);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/street/{street}")
    public ResponseEntity<List<Address>> getAddressByStreet(@PathVariable String street) {
        List<Address> address = addressService.findByStreet(street);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/city/{city}")
    public ResponseEntity<List<Address>> getAddressByCity(@PathVariable String city) {
        List<Address> address = addressService.findByCity(city);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/receipt/{receiptId}")
    public ResponseEntity<List<Receipt>> getReceiptsByAddressId(@PathVariable("receiptId") String receiptId) {
        List<Receipt> receipts = addressService.getReceiptByAddressId(receiptId);
        return ResponseEntity.ok(receipts);
    }

}
