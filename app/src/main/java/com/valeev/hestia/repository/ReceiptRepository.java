package com.valeev.hestia.repository;

import com.valeev.hestia.model.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReceiptRepository extends MongoRepository<Receipt, String> {
}
