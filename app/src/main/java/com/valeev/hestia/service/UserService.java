package com.valeev.hestia.service;


import com.valeev.hestia.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User saveUser(User user);

    User findById(String userId);

    boolean deleteById(String userId);

    User update(User user);

    boolean linkAddressByTelephone(String addressId, String telephone);

    User findByTelephone(String telephone);

}
