package org.example.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegistrationFormDTO {
    @NotBlank(message = "Username name should be not empty")
    private String username;
    @NotBlank(message = "Email name should be not empty")
    @Email
    private String email;
    @NotBlank(message = "Password name should be not empty")
    @Size(min = 5, max = 100, message = "Password should be from 5 to 100 size")
    private String password;
}
