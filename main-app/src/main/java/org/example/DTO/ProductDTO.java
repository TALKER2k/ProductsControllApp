package org.example.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO {
    @NotBlank(message = "Name should be not empty")
    private String name;
    @NotBlank(message = "MeasurementUnit should be not empty")
    private String measurementUnit;
    private Double remainder;
}
