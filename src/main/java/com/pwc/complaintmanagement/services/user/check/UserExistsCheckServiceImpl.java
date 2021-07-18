package com.pwc.complaintmanagement.services.user.check;

import com.pwc.complaintmanagement.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserExistsCheckServiceImpl implements UserExistsCheckService {

    private final UsersRepository usersRepository;

    public UserExistsCheckServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Boolean execute(String username) {
        return usersRepository.existsByUsername(username);
    }
}
