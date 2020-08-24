package com.valeev.hestia.repository;

import com.valeev.hestia.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {
    Address findByReceipts_Id(String id);

    List<Address> findByCityIgnoreCase(String city);

    List<Address> findByStreetIgnoreCase(String street);

    List<Address> findByHouseIgnoreCase(String house);

    List<Address> findByFlatIgnoreCase(String flat);
}
