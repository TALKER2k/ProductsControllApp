package org.example.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CollectProductDTO {
    @NotBlank(message = "Name should be not empty")
    private String name;

    @Min(value = 0)
    private Double collect;
}
