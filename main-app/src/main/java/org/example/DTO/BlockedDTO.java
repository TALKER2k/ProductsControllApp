package org.example.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BlockedDTO {
    @NotEmpty(message = "Username name should be not empty")
    private final String username;
    @NotEmpty(message = "Email name should be not empty")
    private final String email;
}
