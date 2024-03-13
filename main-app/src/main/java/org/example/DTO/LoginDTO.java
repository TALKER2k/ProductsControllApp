package org.example.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {
    @NotEmpty(message = "Username name should be not empty")
    private String username;
    @NotEmpty(message = "Password name should be not empty")
    private String password;
}
