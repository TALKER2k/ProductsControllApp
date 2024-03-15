package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "user_target")
@Data
@Accessors(chain = true)
public class UserTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "remainder")
    @Min(value = 0, message = "Remainder must be non-negative")
    private Double remainder;
}
