package com.valeev.hestia.service.impl;

import com.valeev.hestia.exception.AddressNotFoundException;
import com.valeev.hestia.exception.TelephoneUsedException;
import com.valeev.hestia.exception.UsernameNotFoundException;
import com.valeev.hestia.model.Address;
import com.valeev.hestia.model.User;
import com.valeev.hestia.repository.UserRepository;
import com.valeev.hestia.security.UserPrincipal;
import com.valeev.hestia.service.AddressService;
import com.valeev.hestia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String telephone) {
        User user = userRepository.findByTelephone(telephone);
        if (user == null) {
            throw new UsernameNotFoundException();
        }
        return new UserPrincipal(user);
    }

    @Override
    public User saveUser(User user) {
        User userExcepted = userRepository.findByTelephone(user.getTelephone());
        if (userExcepted != null) {
            throw new TelephoneUsedException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean deleteById(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public User update(User user) {
        User userExcepted = userRepository.findById(user.getId()).orElse(null);
        if (userExcepted == null) {
            return null;
        } else {
            userExcepted = user;
            return userExcepted;
        }
    }

    @Override
    public boolean linkAddressByTelephone(String addressId, String telephone) {
        User user = userRepository.findByTelephone(telephone);
        if (user == null) {
            throw new UsernameNotFoundException();
        }
        Address address = addressService.findById(addressId);
        if (address == null) {
            throw new AddressNotFoundException();
        }
        user.setAddressId(addressId);
        userRepository.save(user);
        return true;
    }
}
