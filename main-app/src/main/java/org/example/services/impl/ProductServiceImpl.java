package org.example.services.impl;

import org.example.DTO.ProductDTO;
import org.example.models.Product;
import org.example.models.UserTarget;
import org.example.models.User;
import org.example.repositories.ProductRepository;
import org.example.repositories.UserTargetRepository;
import org.example.repositories.UserRepository;
import org.example.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserTargetRepository userTargetRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserTargetRepository purposeOfCollectionRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userTargetRepository = purposeOfCollectionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Product addProducts(ProductDTO productDTO) {
        Product product = convertToProduct(productDTO);

        productRepository.save(product);

        List<User> users = userRepository.findAll();

        for (User user : users) {
            UserTarget userTarget = new UserTarget()
                    .setUser(user)
                    .setProduct(product)
                    .setPick(product.getPick());

            userTargetRepository.save(userTarget);
        }

        return product;
    }

    @Override
    public boolean existsByUserName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    private Product convertToProduct(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
