package org.example.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank(message = "Username name should be not empty")
    private String username;
    @NotBlank(message = "Password name should be not empty")
    @Size(min = 5, max = 100, message = "Password should be from 5 to 100 size")
    private String password;
}
