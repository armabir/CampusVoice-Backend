package org.example.campusvoice.repository;

import org.example.campusvoice.model.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends MongoRepository<Complaint, String> {

    Optional<Complaint> findByTrackingId(String trackingId);
}