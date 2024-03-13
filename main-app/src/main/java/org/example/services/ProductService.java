package org.example.services;

import org.example.DTO.ProductDTO;
import org.example.models.Product;

public interface ProductService {
    Product addProducts(ProductDTO productDTO);
    void delete(String product);
    boolean existsByUserName(String name);
}
