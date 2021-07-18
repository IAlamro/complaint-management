package com.pwc.complaintmanagement.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private boolean loggedIn;
    @Enumerated
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Compliant> compliants;
}
