package com.valeev.hestia.config.migration;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.impl.MongockTemplate;
import com.valeev.hestia.constant.StatusEnum;
import com.valeev.hestia.model.Address;
import com.valeev.hestia.model.Receipt;
import com.valeev.hestia.model.Ticket;
import com.valeev.hestia.model.User;
import com.valeev.hestia.repository.UserRepository;
import com.valeev.hestia.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ChangeLog(order = "001")
@AllArgsConstructor
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "Drop collections", author = "valeev")
    public void dropDB(MongockTemplate db) {
        db.dropCollection(Address.COLLECTION_NAME);
        db.dropCollection(Receipt.COLLECTION_NAME);
        db.dropCollection(Ticket.COLLECTION_NAME);
        db.dropCollection(User.COLLECTION_NAME);
    }

    @ChangeSet(order = "002", id = "Add users", author = "valeev")
    public void addUsers(MongockTemplate mongoDatabase, PasswordEncoder passwordEncoder) {
        User admin = new User("+79999999999", "admin", passwordEncoder.encode("admin"), "bbb@as.ru");
        admin.addRole(Role.ADMIN);

        List<User> users = Arrays.asList(
                admin,
                new User("+79999999998", "user", passwordEncoder.encode("user"), "aaa@as.ru")
        );
        mongoDatabase.insertAll(users);
    }

    @ChangeSet(order = "003", id = "Add receipt and addresses", author = "valeev")
    public void addReceipts(MongockTemplate mongoDatabase) {
        Receipt receipt = new Receipt(LocalDate.now(), new BigDecimal("2692.3"), BigDecimal.ONE,
                new BigDecimal("269.2"), new BigDecimal("23.11"), new BigDecimal("64.92"));
        Receipt receipt1 = new Receipt(LocalDate.now().minusMonths(1L), new BigDecimal("3292.2"), BigDecimal.ONE,
                new BigDecimal("269.2"), new BigDecimal("23.11"), new BigDecimal("64.92"));
        Receipt receipt2 = new Receipt(LocalDate.now().minusMonths(2L), new BigDecimal("1292.1"), BigDecimal.ONE,
                new BigDecimal("269.2"), new BigDecimal("23.11"), new BigDecimal("64.92"));
        Receipt receipt3 = new Receipt(LocalDate.now().minusMonths(3L), new BigDecimal("4292.2"), BigDecimal.ONE,
                new BigDecimal("269.2"), new BigDecimal("23.11"), new BigDecimal("64.92"));
        mongoDatabase.insertAll(Arrays.asList(receipt, receipt1, receipt2, receipt3));

        Address address = new Address("Казань", "Большая красная", "8", "41");
        Address address2 = new Address("Казань", "Маленькая красная", "12", "32");
        address2.addReceipt(receipt1, receipt2, receipt3);
        address.addReceipt(receipt);

        mongoDatabase.insertAll(Arrays.asList(address, address2));

    }

    @ChangeSet(order = "004", id = "Add ticket", author = "valeev")
    public void addTicket(MongockTemplate mongoDatabase, UserRepository userRepository) {
        User users = userRepository.findAll().stream()
                .findFirst()
                .orElseThrow();
        List<Ticket> tickets = Arrays.asList(
                new Ticket(users.getId(), "Проблема со светом", "Не работает лампочка в корридоре",
                        StatusEnum.SUCCESS),
                new Ticket(users.getId(), "Проблема со светом 2", "Не работает лампочка в 2ом корридоре",
                        StatusEnum.WORK),
                new Ticket(users.getId(), "Проблема со светом 3", "Не работает лампочка в 3ом корридоре",
                        StatusEnum.CREATE)
        );
        mongoDatabase.insertAll(tickets);
    }
}
