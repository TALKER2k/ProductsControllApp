package org.example.services.impl;

import org.example.DTO.BlockedDTO;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.example.services.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public void dismissalUser(BlockedDTO blockedDTO) {
        User user = userRepository.findByEmail(blockedDTO.getEmail()).orElseThrow(() ->
                new RuntimeException("User not found"));
        if (user.getUserName().equals(blockedDTO.getUsername())) {
            user.setActive(false);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Username and email not equals");
        }
    }
}
