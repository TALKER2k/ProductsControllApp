package org.example.DTO;

import jakarta.validation.constraints.NotEmpty;

public record LoginDTO(
        @NotEmpty(message = "Username name should be not empty") String username,
        @NotEmpty(message = "Password name should be not empty") String password
) {
}