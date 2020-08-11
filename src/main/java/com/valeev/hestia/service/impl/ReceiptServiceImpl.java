package com.valeev.hestia.service.impl;

import com.valeev.hestia.model.Receipt;
import com.valeev.hestia.repository.ReceiptRepository;
import com.valeev.hestia.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;

    @Override
    public List<Receipt> allReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public Receipt getReceiptById(String id) {
        return receiptRepository.findById(id)
                .orElse(null);
    }

}
