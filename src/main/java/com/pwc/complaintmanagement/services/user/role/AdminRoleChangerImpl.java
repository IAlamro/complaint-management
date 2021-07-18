package com.pwc.complaintmanagement.services.user.role;

import com.pwc.complaintmanagement.entities.Role;
import com.pwc.complaintmanagement.entities.User;
import com.pwc.complaintmanagement.repositories.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleChangerImpl implements AdminRoleChanger {

    private final UsersRepository usersRepository;

    public AdminRoleChangerImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void execute(Long userId) {
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN.name())){
            User user = usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            user.setRole(Role.ADMIN);
            usersRepository.save(user);
        }
    }
}
