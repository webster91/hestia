package com.valeev.hestia.utils.mapper;

import com.valeev.hestia.dto.ReceiptDto;
import com.valeev.hestia.model.Receipt;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReceiptMapper {

    ReceiptDto toReceiptDto(Receipt receipt);

    Receipt toReceipt(ReceiptDto receiptDto);
}
