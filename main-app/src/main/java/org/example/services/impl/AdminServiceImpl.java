package org.example.services.impl;

import org.example.DTO.BlockedUserDTO;
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
    public void dismissalUser(BlockedUserDTO blockedUserDTO) {
        User userForBlock = userRepository.findByEmail(blockedUserDTO.email()).orElseThrow(() ->
                new RuntimeException("User not found"));
        if (userForBlock.getUserName().equals(blockedUserDTO.username())) {
            userForBlock.setActive(false);
            userRepository.save(userForBlock);
        } else {
            throw new RuntimeException("Username and email not equals");
        }
    }
}
