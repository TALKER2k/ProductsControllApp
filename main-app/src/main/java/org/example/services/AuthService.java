package org.example.services;

import org.example.DTO.LoginDTO;
import org.example.DTO.RegisterDTO;
import org.example.models.User;
import org.springframework.validation.BindingResult;

public interface AuthService {
    void registerAdmin(Integer id);
    User registerUser(RegisterDTO registerDto);
    String loginUser(LoginDTO loginDto);
    void checkErrors(BindingResult bindingResult);
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
}
