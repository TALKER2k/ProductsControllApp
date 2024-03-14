package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_product")
    private String name;

    @Column(name = "measurement_unit")
    private String measurementUnit;

    @Column(name = "remainder")
    @Min(value = 0, message = "Remainder must be non-negative")
    private Double remainder;
}
