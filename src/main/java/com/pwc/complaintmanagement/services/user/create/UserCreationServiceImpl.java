package com.pwc.complaintmanagement.services.user.create;

import com.pwc.complaintmanagement.entities.Role;
import com.pwc.complaintmanagement.entities.User;
import com.pwc.complaintmanagement.repositories.UsersRepository;
import com.pwc.complaintmanagement.services.user.check.UserAlreadyExistsException;
import com.pwc.complaintmanagement.services.user.check.UserExistsCheckService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreationServiceImpl implements UserCreationService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final UserExistsCheckService userExistsCheckService;

    public UserCreationServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository, UserExistsCheckService userExistsCheckService) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.userExistsCheckService = userExistsCheckService;
    }

    @Override
    public Long execute(UserCreationDto userCreationDto) {
        if (userExistsCheckService.execute(userCreationDto.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        return usersRepository.save(getUser(userCreationDto)).getId();
    }

    private User getUser(UserCreationDto userCreationDto) {
        User user = new User();

        user.setUsername(userCreationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
        user.setRole(Role.USER);

        return user;
    }
}
