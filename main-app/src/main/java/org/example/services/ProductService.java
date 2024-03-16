package org.example.services;

import org.example.DTO.ProductDTO;
import org.example.models.Product;

public interface ProductService {
    Product addProducts(ProductDTO productDTO);

    boolean existsByUserName(String name);
}
