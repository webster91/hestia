package com.valeev.hestia.service.impl;

import com.valeev.hestia.model.Ticket;
import com.valeev.hestia.repository.TicketRepository;
import com.valeev.hestia.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> allTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(String id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public boolean deleteById(String id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
