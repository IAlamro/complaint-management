package com.pwc.complaintmanagement.services.complaient.create;

import com.pwc.complaintmanagement.entities.User;
import lombok.Data;

@Data
public class ComplaintDto {

    private String name;
    private User user;
}
