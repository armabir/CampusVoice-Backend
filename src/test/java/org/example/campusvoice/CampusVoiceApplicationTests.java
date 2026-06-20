package org.example.campusvoice;

import org.example.campusvoice.model.Complaint;
import org.example.campusvoice.model.User;
import org.example.campusvoice.service.ComplaintService;
import org.example.campusvoice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CampusVoiceApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ComplaintService complaintService;

    @Test
    void userCreation() {
        User user = new User();
        user.setId("101");
        user.setName("ARM ABIR HASAN");
        user.setRole("STUDENT");
        user.setEmail("176@seu.edu.bd");
        user.setPassword("asd");

        userService.createUser(user);
    }

    @Test
    void complaintCheck(){
        Complaint complaint = new Complaint();
        complaint.setId("1001");
        complaint.setAnonymous(true);
        complaint.setPublic(true);
        complaint.setCategory("IT");
        complaint.setCreatorId("101");
        complaint.setTitle("Testing");
        complaint.setDescription("What can I say now?");
        complaint.setPriority("MEDIUM");
        complaint.setMediaUrls(List.of("https://media.assettype.com/outlookmoney/2025-03-23/ymbh3m0p/Indias-IT-sector.jpg?w=1200&h=675&auto=format%2Ccompress&fit=max&enlarge=true"));

        complaintService.createComplaint(complaint);
    }

}
