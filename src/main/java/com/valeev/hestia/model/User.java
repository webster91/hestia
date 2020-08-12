package com.valeev.hestia.model;

import com.valeev.hestia.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = User.COLLECTION_NAME)
public class User {
    public static final String COLLECTION_NAME = "user";

    @Id
    private String id;

    @Field
    @NotBlank
    private String username;

    @Field
    @NotBlank
    private String password;

    @Field
    @NotBlank
    private Set<Role> roles = new HashSet<>();

    @Field
    private String addressId;

    @Field
    private String email;

    @Field
    private String telephone;

    public User(@NotBlank String username, @NotBlank String password, @NotBlank Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
