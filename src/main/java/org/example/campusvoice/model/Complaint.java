package org.example.campusvoice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "complaints")
public class Complaint {

    @Id
    private String id;

    private String trackingId;

    private String title;

    private String description;

    private String category; // e.g., "IT", "Maintenance", "Office"

    private String status; // "PENDING", "ASSIGNED", "IN_PROGRESS", "RESOLVED"

    private String priority; // "LOW", "MEDIUM", "HIGH", "URGENT"

    private boolean isAnonymous;

    private boolean isPublic;

    private int upvotes;

    private List<String> comments = new ArrayList<>();
    private List<String> mediaUrls = new ArrayList<>();

    private String creatorId; // Reference to the User ID

    private LocalDateTime createdAt;
}