package com.valeev.hestia.service;

import com.valeev.hestia.model.Address;
import com.valeev.hestia.model.Receipt;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address findById(String id);

    List<Address> findByStreet(String street);

    List<Address> findByHouse(String house);

    List<Address> findByCity(String house);

    Address save(Address address);

    Address update(Address address);

    boolean deleteById(String id);

    List<Receipt> getReceiptByAddressId(String id);
}
