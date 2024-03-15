package org.example.DTO;

import jakarta.validation.constraints.NotEmpty;

public record BlockedUserDTO(
        @NotEmpty(message = "Username name should be not empty") String username,
        @NotEmpty(message = "Email name should be not empty") String email
) {
}
