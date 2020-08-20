package com.valeev.hestia.controller;

import com.valeev.hestia.dto.TicketDto;
import com.valeev.hestia.model.Ticket;
import com.valeev.hestia.service.TicketService;
import com.valeev.hestia.utils.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable("ticketId") String ticketId) {
        Ticket ticket = ticketService.getById(ticketId);
        TicketDto ticketDto = ticketMapper.toTicketDto(ticket);
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping("/ticket/user/{userId}")
    public ResponseEntity<List<TicketDto>> getTicketByUserId(@PathVariable("userId") String userId) {
        List<TicketDto> tickets = ticketService.getByUserId(userId).stream()
                .map(ticketMapper::toTicketDto)
                .collect(Collectors.toList());;
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/ticket")
    public ResponseEntity<List<TicketDto>> allTickets() {
        List<TicketDto> tickets = ticketService.allTickets().stream()
                .map(ticketMapper::toTicketDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/ticket")
    public ResponseEntity<TicketDto> saveTicket(@RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketMapper.toTicket(ticketDto);
        Ticket ticketRs = ticketService.save(ticket);
        TicketDto ticketDtoRs = ticketMapper.toTicketDto(ticketRs);
        return ResponseEntity.ok(ticketDtoRs);
    }

}
