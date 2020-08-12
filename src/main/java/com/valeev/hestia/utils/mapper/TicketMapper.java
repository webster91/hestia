package com.valeev.hestia.utils.mapper;

import com.valeev.hestia.dto.TicketDto;
import com.valeev.hestia.model.Ticket;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TicketMapper {

    TicketDto toTicketDto(Ticket ticket);

    Ticket toTicket(TicketDto ticketDto);
}
