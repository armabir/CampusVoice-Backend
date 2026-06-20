package org.example.campusvoice.controller;

import org.example.campusvoice.model.Complaint;
import org.example.campusvoice.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return complaintService.createComplaint(complaint);
    }

    @GetMapping("/{id}")
    public Complaint fetchComplaint(@PathVariable String id) {
        return complaintService.getComplaintById(id);
    }

    // New Endpoint: Track by Tracking ID
    @GetMapping("/track/{trackingId}")
    public Complaint fetchByTrackingId(@PathVariable String trackingId) {
        return complaintService.getComplaintByTrackingId(trackingId);
    }

    // New Endpoint: Upvote
    @PatchMapping("/{id}/upvote")
    public Complaint upvote(@PathVariable String id) {
        return complaintService.upvoteComplaint(id);
    }

    // New Endpoint: Add Comment
    @PatchMapping("/{id}/comment")
    public Complaint addComment(@PathVariable String id, @RequestBody String comment) {
        return complaintService.addComment(id, comment);
    }

    @GetMapping
    public List<Complaint> fetchAllComplaints() {
        return complaintService.getAllComplaints();
    }
}