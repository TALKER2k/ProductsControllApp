package org.example.services.impl;

import org.example.DTO.ProductDTO;
import org.example.models.Product;
import org.example.models.ProductUser;
import org.example.models.User;
import org.example.repositories.ProductRepository;
import org.example.repositories.ProductUserRepository;
import org.example.repositories.UserRepository;
import org.example.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductUserRepository productUserRepository;
    private  final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductUserRepository purposeOfCollectionRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productUserRepository = purposeOfCollectionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Product addProducts(ProductDTO productDTO) {
        Product product = convertToProduct(productDTO);

        productRepository.save(product);

        List<User> users = userRepository.findAll();

        for (User user : users) {
            ProductUser productUser = new ProductUser();
            productUser.setUser(user);
            productUser.setProduct(product);
            productUser.setRemainder(productDTO.getRemainder());

            productUserRepository.save(productUser);
        }

        return product;
    }

    private Product convertToProduct(ProductDTO productDTO) {
        return new Product().setName(productDTO.getName())
                .setMeasurementUnit(productDTO.getMeasurementUnit())
                .setRemainder(productDTO.getRemainder());
    }

    @Override
    public void delete(String product) {
        productRepository.delete(productRepository.findByName(product)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }

    @Override
    public boolean existsByUserName(String name) {
        return productRepository.existsByName(name);
    }
}
