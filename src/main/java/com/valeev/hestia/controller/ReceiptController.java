package com.valeev.hestia.controller;

import com.valeev.hestia.dto.ReceiptDto;
import com.valeev.hestia.model.Receipt;
import com.valeev.hestia.service.ReceiptService;
import com.valeev.hestia.utils.mapper.ReceiptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ReceiptMapper receiptMapper;

    @GetMapping("/receipt")
    public ResponseEntity<List<ReceiptDto>> getReceipts() {
        List<ReceiptDto> receipts = receiptService.allReceipts().stream()
                .map(receiptMapper::toReceiptDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/receipt/{receiptId}")
    public ResponseEntity<ReceiptDto> getReceipt(@PathVariable("receiptId") String receiptId) {
        Receipt receipt = receiptService.getReceiptById(receiptId);
        return ResponseEntity.ok(receiptMapper.toReceiptDto(receipt));
    }
}
