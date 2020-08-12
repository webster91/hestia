package com.valeev.hestia.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Calendar;

@Data
@NoArgsConstructor
public class ReceiptDto {
    private String id;
    private Calendar dateInterest;
    private BigDecimal sum;
    private BigDecimal arrears;
    private BigDecimal hotWater;
    private BigDecimal coldWater;
    private BigDecimal electricity;
}
