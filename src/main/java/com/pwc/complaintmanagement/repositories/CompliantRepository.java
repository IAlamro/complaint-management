package com.pwc.complaintmanagement.repositories;

import com.pwc.complaintmanagement.entities.Compliant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompliantRepository extends JpaRepository<Compliant, Long> {
}
