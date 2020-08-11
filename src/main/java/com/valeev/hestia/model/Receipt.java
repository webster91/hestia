package com.valeev.hestia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Calendar;

@Data
@NoArgsConstructor
@Document(collection = Receipt.COLLECTION_NAME)
public class Receipt {
    public static final String COLLECTION_NAME = "receipt";

    @Id
    private String id;

    @Field
    @NotBlank
    private Calendar dateInterest;

    @Field
    private BigDecimal sum;

    @Field
    private BigDecimal arrears;

    @Field
    private BigDecimal hotWater;

    @Field
    private BigDecimal coldWater;

    @Field
    private BigDecimal electricity;

    public Receipt(@NotBlank Calendar dateInterest, BigDecimal sum, BigDecimal arrears,
                   BigDecimal hotWater, BigDecimal coldWater, BigDecimal electricity) {
        this.dateInterest = dateInterest;
        this.sum = sum;
        this.arrears = arrears;
        this.hotWater = hotWater;
        this.coldWater = coldWater;
        this.electricity = electricity;
    }
}
