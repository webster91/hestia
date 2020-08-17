package com.valeev.hestia.model;

import com.google.common.collect.Sets;
import com.valeev.hestia.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = User.COLLECTION_NAME)
public class User {
    public static final String COLLECTION_NAME = "user";

    @Id
    private String id;

    @Field
    @Indexed(unique = true)
    private String telephone;

    @Field
    @NotBlank
    private String username;

    @Field
    @NotBlank
    private String password;

    @Field
    @Email
    private String email;

    @Field
    @NotBlank
    private Set<Role> roles = Sets.newHashSet(Role.USER);

    @Field
    private String addressId;

    public User(String telephone, @NotBlank String username, @NotBlank String password, @Email String email) {
        this.telephone = telephone;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
