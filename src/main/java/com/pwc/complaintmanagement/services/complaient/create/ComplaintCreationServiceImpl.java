package com.pwc.complaintmanagement.services.complaient.create;

import com.pwc.complaintmanagement.entities.ComplaintStatus;
import com.pwc.complaintmanagement.entities.Compliant;
import com.pwc.complaintmanagement.repositories.CompliantRepository;
import org.springframework.stereotype.Service;

@Service
public class ComplaintCreationServiceImpl implements ComplaintCreationService {

    private final CompliantRepository compliantRepository;

    public ComplaintCreationServiceImpl(CompliantRepository compliantRepository) {
        this.compliantRepository = compliantRepository;
    }

    @Override
    public Long execute(ComplaintDto complaintDto) {
        return compliantRepository.save(getCompliant(complaintDto)).getId();
    }

    private Compliant getCompliant(ComplaintDto complaintDto) {
        Compliant compliant = new Compliant();
        compliant.setId(compliant.getId());
        compliant.setName(complaintDto.getName());
        compliant.setStatus(ComplaintStatus.CREATED);
        compliant.setUser(complaintDto.getUser());
        return compliant;
    }
}
