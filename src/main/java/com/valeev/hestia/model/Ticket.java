package com.valeev.hestia.model;

import com.valeev.hestia.constant.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@Document(collection = Ticket.COLLECTION_NAME)
public class Ticket {
    public static final String COLLECTION_NAME = "ticket";

    @Id
    private String id;
    @Field
    private String userId;
    @Field
    private String header;
    @Field
    private String description;
    @Field
    private StatusEnum status;

    public Ticket(String userId, String header, String description, StatusEnum status) {
        this.userId = userId;
        this.header = header;
        this.description = description;
        this.status = status;
    }
}
