package com.pwc.complaintmanagement.services.complaient.status.change;

import com.pwc.complaintmanagement.entities.ComplaintStatus;
import lombok.Data;

@Data
public class StatusChangeRequest {

    private long id;
    private ComplaintStatus complaintStatus;
}
