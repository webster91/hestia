package com.valeev.hestia.service.impl;

import com.valeev.hestia.model.Address;
import com.valeev.hestia.model.Receipt;
import com.valeev.hestia.repository.AddressRepository;
import com.valeev.hestia.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public List<Receipt> getReceiptByAddressId(String id) {
        Address addressByReceiptsIdLike = addressRepository.findByReceipts_Id(id);
        return addressByReceiptsIdLike.getReceipts();
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(String id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findByStreet(String street) {
        return addressRepository.findByStreetIgnoreCase(street);
    }

    @Override
    public List<Address> findByHouse(String house) {
        return addressRepository.findByHouseIgnoreCase(house);
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressRepository.findByCityIgnoreCase(city);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        if (!addressRepository.existsById(address.getId())) {
            return null;
        }
        return addressRepository.save(address);
    }

    @Override
    public boolean deleteById(String id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
