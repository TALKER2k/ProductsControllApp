package org.example.controllers;

import org.example.DTO.BlockedUserDTO;
import org.example.services.AdminService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PatchMapping("/dismissal")
    public void setUserActiveToFalse(@RequestBody BlockedUserDTO blockedUserDTO) {
        adminService.dismissalUser(blockedUserDTO);
    }
}
