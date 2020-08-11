package com.valeev.hestia.config.migration;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.impl.MongockTemplate;
import com.google.common.collect.Sets;
import com.mongodb.client.MongoDatabase;
import com.valeev.hestia.model.Address;
import com.valeev.hestia.model.Receipt;
import com.valeev.hestia.model.User;
import com.valeev.hestia.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@ChangeLog(order = "001")
@AllArgsConstructor
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "Drop DB1", author = "valeev")
    public void dropDB(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "Add users", author = "valeev")
    public void addUsers(MongockTemplate mongoDatabase, PasswordEncoder passwordEncoder) {
        List<User> users = Arrays.asList(
                new User("user", passwordEncoder.encode("user"), Sets.newHashSet(Role.USER)),
                new User("admin", passwordEncoder.encode("admin"), Sets.newHashSet(Role.ADMIN))
        );
        mongoDatabase.insertAll(users);
    }

    @ChangeSet(order = "003", id = "Add receipt and addresses", author = "valeev")
    public void addReceipts(MongockTemplate mongoDatabase) {
        Receipt receipt = new Receipt(Calendar.getInstance(), BigDecimal.ONE, BigDecimal.ZERO,
                new BigDecimal("269.2"), new BigDecimal("23.11"), new BigDecimal("64.92"));
        Receipt receipt1 = new Receipt(Calendar.getInstance(), BigDecimal.ONE, BigDecimal.ZERO,
                new BigDecimal("269.2"), new BigDecimal("23.11"), new BigDecimal("64.92"));
        mongoDatabase.insertAll(Arrays.asList(receipt1, receipt));

        Address address = new Address("Казань", "Большая красная", "8", "41");
        Address address2 = new Address("Казань", "Маленькая красная", "12", "32");
        address2.addReceipt(receipt1);
        address.addReceipt(receipt);

        mongoDatabase.insertAll(Arrays.asList(address, address2));

    }
}