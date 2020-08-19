package com.valeev.hestia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = Address.COLLECTION_NAME)
public class Address {
    public static final String COLLECTION_NAME = "address";

    @Id
    private String id;

    @Field
    @NotBlank
    private String city;

    @Field
    private String street;

    @Field
    private String house;

    @Field
    private String flat;

    @Field
    private List<Receipt> receipts = new ArrayList<>();

    public Address(@NotBlank String city, String street, String house, String flat) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public void addReceipt(Receipt... receipt) {
        receipts.addAll(Arrays.asList(receipt));
    }
}

