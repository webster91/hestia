package com.valeev.hestia.utils.mapper;

import com.valeev.hestia.dto.TicketDto;
import com.valeev.hestia.model.Ticket;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TicketMapper {

    @Mapping(target = "status", source = "status.name")
    TicketDto toTicketDto(Ticket ticket);

    Ticket toTicket(TicketDto ticketDto);

}
