package com.valeev.hestia.repository;

import com.valeev.hestia.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
