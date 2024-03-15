package org.example.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PickProductDTO(
        @NotBlank(message = "Name should be not empty") String name,

        @Min(value = 0) Double pick
) {
}
