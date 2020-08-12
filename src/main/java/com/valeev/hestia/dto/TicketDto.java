package com.valeev.hestia.dto;

import com.valeev.hestia.constant.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDto {
    private String id;
    private String userId;
    private String header;
    private String description;
    private StatusEnum status;
}
