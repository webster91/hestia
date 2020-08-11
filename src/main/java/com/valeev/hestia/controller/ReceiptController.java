package com.valeev.hestia.controller;

import com.valeev.hestia.model.Receipt;
import com.valeev.hestia.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;

    @GetMapping("/receipt")
    public ResponseEntity<List<Receipt>> getReceipts() {
        List<Receipt> receipts = receiptService.allReceipts();
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/receipt/{receiptId}")
    public ResponseEntity<Receipt> getReceipt(@PathVariable("receiptId") String receiptId) {
        Receipt receipt = receiptService.getReceiptById(receiptId);
        return ResponseEntity.ok(receipt);
    }
}
