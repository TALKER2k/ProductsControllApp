package org.example.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "User Content. Your username " +  auth.getName();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAuthAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Admin Content. Your username " +  auth.getName();
    }
}
