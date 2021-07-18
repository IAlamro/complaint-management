package com.pwc.complaintmanagement.controllers;

import com.pwc.complaintmanagement.services.complaient.create.ComplaintCreationService;
import com.pwc.complaintmanagement.services.complaient.create.ComplaintDto;
import com.pwc.complaintmanagement.services.complaient.status.change.StatusChangeRequest;
import com.pwc.complaintmanagement.services.complaient.status.change.StatusChangeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplaintsController {

    private final ComplaintCreationService complaintCreationService;
    private final StatusChangeService statusChangeService;

    public ComplaintsController(ComplaintCreationService complaintCreationService, StatusChangeService statusChangeService) {
        this.complaintCreationService = complaintCreationService;
        this.statusChangeService = statusChangeService;
    }

    @PostMapping("/user/complaints")
    public Long createComplaint(ComplaintDto complaintDto){
        return complaintCreationService.execute(complaintDto);
    }

    @PutMapping("/admin/complaints/status")
    public void changeComplaintStatus(StatusChangeRequest statusChangeRequest){
        statusChangeService.execute(statusChangeRequest);
    }
}
