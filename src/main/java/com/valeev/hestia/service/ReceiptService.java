package com.valeev.hestia.service;

import com.valeev.hestia.model.Receipt;

import java.util.List;

public interface ReceiptService {

    List<Receipt> allReceipts();

    Receipt getReceiptById(String id);

}
