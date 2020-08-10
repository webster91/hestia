package com.valeev.hestia.dao;


import com.valeev.hestia.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, String> {
    User findByUsername(String username);
}
