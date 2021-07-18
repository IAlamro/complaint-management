package com.pwc.complaintmanagement.services.complaient.status.change;

import com.pwc.complaintmanagement.entities.Compliant;
import com.pwc.complaintmanagement.entities.Role;
import com.pwc.complaintmanagement.repositories.CompliantRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StatusChangeServiceImpl implements StatusChangeService {

    private final CompliantRepository compliantRepository;

    public StatusChangeServiceImpl(CompliantRepository compliantRepository) {
        this.compliantRepository = compliantRepository;
    }

    @Override
    public void execute(StatusChangeRequest statusChangeRequest) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN.name())){
            Compliant compliant = compliantRepository
                    .findById(statusChangeRequest.getId())
                    .orElseThrow(ComplaintNotFoundException::new);
            compliant.setStatus(statusChangeRequest.getComplaintStatus());
            compliantRepository.save(compliant);
        } else {
            throw new ForbiddenStatusChangeException();
        }
    }
}
