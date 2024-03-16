package org.example.models;

import jakarta.persistence.*;
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

    @Column(name = "pick")
    private Double pick;
}
