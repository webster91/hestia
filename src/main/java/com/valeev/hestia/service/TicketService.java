package com.valeev.hestia.service;

import com.valeev.hestia.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> allTickets();

    Ticket getById(String id);

    List<Ticket> getByUserId(String userId);

    Ticket save(Ticket ticket);

    boolean deleteById(String id);
}
