package org.example.controllers;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.DTO.AuthorizationResponseDTO;
import org.example.DTO.LoginDTO;
import org.example.DTO.RegistrationFormDTO;
import org.example.models.User;
import org.example.services.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthorizationResponseDTO> login(@RequestBody LoginDTO loginDto) {

        String token = authorizationService.loginUser(loginDto);

        return ResponseEntity.ok()
                .header("Server message", "Login succeeded")
                .body(new AuthorizationResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegistrationFormDTO registrationFormDto,
                                         BindingResult bindingResult) {

        if (authorizationService.existsByUserName(registrationFormDto.getUsername())) {
            return ResponseEntity.badRequest()
                    .header("Server message", "Username is taken")
                    .build();
        } else if (authorizationService.existsByEmail(registrationFormDto.getEmail())) {
            return ResponseEntity.badRequest()
                    .header("Server message", "Email is taken")
                    .build();
        }

        authorizationService.checkErrors(bindingResult);

        User user = authorizationService.registerUser(registrationFormDto);

        return ResponseEntity.ok()
                .header("Server message", "User registered successfully")
                .body(user);
    }

    @SneakyThrows
    @PostMapping("/registerAdmin/{id}")
    public ResponseEntity<String> registerAdmin(@PathVariable Integer id) {
        try {
            authorizationService.registerAdmin(id);
            return ResponseEntity.ok()
                    .header("Server message", "User applied to admin role successfully")
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .header("Server message", "User applied to admin role unsuccessfully")
                    .body(ex.getMessage());
        }
    }

}
