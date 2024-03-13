package org.example.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.DTO.AuthResponseDTO;
import org.example.DTO.LoginDTO;
import org.example.DTO.RegisterDTO;
import org.example.models.User;
import org.example.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto) {

        String token = authService.loginUser(loginDto);

        return ResponseEntity.ok()
                .header("Server message", "Login succeeded")
                .body(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO registerDto,
                                         BindingResult bindingResult) {

        if (authService.existsByUserName(registerDto.getUsername())) {
            return ResponseEntity.badRequest()
                    .header("Server message", "Username is taken")
                    .build();
        }

        authService.checkErrors(bindingResult);

        User user = authService.registerUser(registerDto);

        return ResponseEntity.ok()
                .header("Server message", "User registered successfully")
                .body(user);
    }

    @PostMapping("/registerAdmin/{id}")
    public ResponseEntity<String> registerAdmin(@PathVariable Integer id) {
        try {
            authService.registerAdmin(id);
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
