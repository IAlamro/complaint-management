package com.pwc.complaintmanagement.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Compliant {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated
    private ComplaintStatus status;
}
