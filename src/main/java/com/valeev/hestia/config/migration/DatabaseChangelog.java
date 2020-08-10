package com.valeev.hestia.config.migration;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.google.common.collect.Sets;
import com.mongodb.client.MongoDatabase;
import com.valeev.hestia.domain.User;
import com.valeev.hestia.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@ChangeLog(order = "001")
@AllArgsConstructor
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "Drop DB", author = "valeev")
    public void dropDB(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "Add users", author = "valeev")
    public void addUsers(MongoTemplate mongoDatabase, PasswordEncoder passwordEncoder) {
        List<User> users = Arrays.asList(
                new User("user", passwordEncoder.encode("user"), Sets.newHashSet(Role.USER)),
                new User("admin", passwordEncoder.encode("admin"), Sets.newHashSet(Role.ADMIN))
        );
        mongoDatabase.insertAll(users);
    }
}
