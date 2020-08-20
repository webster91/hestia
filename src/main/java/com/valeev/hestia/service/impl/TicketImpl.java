package com.valeev.hestia.service.impl;

import com.valeev.hestia.constant.StatusEnum;
import com.valeev.hestia.exception.StatusNotFoundException;
import com.valeev.hestia.exception.UserNotFoundException;
import com.valeev.hestia.model.Ticket;
import com.valeev.hestia.repository.TicketRepository;
import com.valeev.hestia.service.TicketService;
import com.valeev.hestia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;

    @Override
    public List<Ticket> allTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(String id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ticket> getByUserId(String userId) {
        return ticketRepository.findByUserId(userId);
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (userService.findById(ticket.getUserId()) == null) {
            throw new UserNotFoundException();
        }
        ticket.setStatus(StatusEnum.CREATE);
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

    @Override
    public boolean changeStatus(String ticketId, String status) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(UserNotFoundException::new);
        StatusEnum statusEnum = StatusEnum.fromName(status);
        if (statusEnum == null) {
            throw new StatusNotFoundException();
        }
        ticket.setStatus(statusEnum);
        ticketRepository.save(ticket);
        return true;
    }
}
