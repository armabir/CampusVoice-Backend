package org.example.campusvoice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true) // Ensures email remains unique in the DB
    private String email;

    private String password; // Remember to store this as a hashed string!

    private String role; // e.g., "STUDENT", "STAFF", "ADMIN"
}