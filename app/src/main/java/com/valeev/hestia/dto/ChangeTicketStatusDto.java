package com.valeev.hestia.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeTicketStatusDto {
    private String ticketId;
    private String status;
}
