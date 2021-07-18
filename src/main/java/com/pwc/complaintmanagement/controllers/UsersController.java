package com.pwc.complaintmanagement.controllers;

import com.pwc.complaintmanagement.services.user.check.UserExistsCheckService;
import com.pwc.complaintmanagement.services.user.create.UserCreationDto;
import com.pwc.complaintmanagement.services.user.create.UserCreationService;
import com.pwc.complaintmanagement.services.user.role.AdminRoleChanger;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    private final UserCreationService userCreationService;
    private final AdminRoleChanger adminRoleChanger;
    private final UserExistsCheckService userExistsCheckService;

    public UsersController(UserCreationService userCreationService, AdminRoleChanger adminRoleChanger, UserExistsCheckService userExistsCheckService) {
        this.userCreationService = userCreationService;
        this.adminRoleChanger = adminRoleChanger;
        this.userExistsCheckService = userExistsCheckService;
    }

    @PostMapping("/user/users")
    public Long register(UserCreationDto userCreationDto) {
        return userCreationService.execute(userCreationDto);
    }

    @PutMapping("/admin/users/{userId}/role/ADMIN")
    public void changeUserRole(@PathVariable Long userId){
        adminRoleChanger.execute(userId);
    }

    @GetMapping("/user/users/{username}")
    public boolean userExists(@PathVariable String username){
        return userExistsCheckService.execute(username);
    }
}
