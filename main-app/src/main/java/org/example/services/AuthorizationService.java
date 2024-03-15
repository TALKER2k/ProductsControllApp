package org.example.services;

import org.example.DTO.LoginDTO;
import org.example.DTO.RegistrationFormDTO;
import org.example.models.User;
import org.springframework.validation.BindingResult;

public interface AuthorizationService {
    void registerAdmin(Integer id);

    User registerUser(RegistrationFormDTO registrationFormDto);

    String loginUser(LoginDTO loginDto);

    void checkErrors(BindingResult bindingResult);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
