package org.example.campusvoice.service;

import org.example.campusvoice.model.Complaint;
import org.example.campusvoice.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public Complaint createComplaint(Complaint complaint) {
        // Generate a unique, human-readable Tracking ID
        String uniqueId = "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        complaint.setTrackingId(uniqueId);
        complaint.setStatus("PENDING");
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setUpvotes(0);

        return complaintRepository.save(complaint);
    }

    // New Function: Find by Tracking ID
    public Complaint getComplaintByTrackingId(String trackingId) {
        return complaintRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with tracking ID: " + trackingId));
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint getComplaintById(String id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
    }

    public Complaint updateComplaintStatus(String id, String status) {
        Complaint complaint = getComplaintById(id);
        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }

    public void deleteComplaint(String id) {
        if (!complaintRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: Complaint not found");
        }
        complaintRepository.deleteById(id);
    }

    // New Function: Upvote a Complaint
    public Complaint upvoteComplaint(String id) {
        Complaint complaint = getComplaintById(id);
        complaint.setUpvotes(complaint.getUpvotes() + 1);
        return complaintRepository.save(complaint);
    }

    // New Function: Add a Comment
    public Complaint addComment(String id, String comment) {
        Complaint complaint = getComplaintById(id);

        // Add the new comment to the list
        List<String> comments = complaint.getComments();
        comments.add(comment);
        complaint.setComments(comments);

        return complaintRepository.save(complaint);
    }
}