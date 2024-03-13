package org.example.repositories;

import org.example.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Boolean existsByName(String productName);

    Optional<Product> findByName(String name);
}
