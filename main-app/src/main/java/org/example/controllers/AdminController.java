package org.example.controllers;

import org.example.DTO.BlockedDTO;
import org.example.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PatchMapping("/dismissal")
    public ResponseEntity<?> setUserActiveToFalse(@RequestBody BlockedDTO blockedDTO) {
        adminService.dismissalUser(blockedDTO);
        return ResponseEntity.ok().build();
    }
}
