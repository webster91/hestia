package com.valeev.hestia.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDto {
    private String id;
    private String userId;
    private String header;
    private String description;
}
