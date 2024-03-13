package org.example.services.impl;

import org.example.DTO.CollectProductDTO;
import org.example.DTO.ProductDTO;
import org.example.models.HistoryCollectProduct;
import org.example.models.Product;
import org.example.models.ProductUser;
import org.example.models.User;
import org.example.repositories.HistoryCollectProductRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.ProductUserRepository;
import org.example.repositories.UserRepository;
import org.example.services.ProductCollectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductCollectServiceImpl implements ProductCollectService {
    private final HistoryCollectProductRepository historyCollectProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductUserRepository productUserRepository;

    public ProductCollectServiceImpl(HistoryCollectProductRepository historyCollectProductRepository, UserRepository userRepository, ProductRepository productRepository, ProductUserRepository productUserRepository) {
        this.historyCollectProductRepository = historyCollectProductRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productUserRepository = productUserRepository;
    }

    @Override
    public void collectProduct(CollectProductDTO collectProductDTO, String username) {
        User user = userRepository.findByUserName(username).orElseThrow();
        Product product = productRepository.findByName(collectProductDTO.getName()).orElseThrow();
        HistoryCollectProduct historyCollectProduct = new HistoryCollectProduct()
                .setCollect(collectProductDTO.getCollect())
                .setCollectedAt(LocalDateTime.now())
                .setUser(user)
                .setProduct(product);

        historyCollectProductRepository.save(historyCollectProduct);

        ProductUser productUser = productUserRepository.findByUserIdAndProductId(user.getId(), product.getId());
        productUser.setRemainder(product.getRemainder() - collectProductDTO.getCollect());
        productUserRepository.save(productUser);
    }

    @Override
    public List<ProductDTO> getListProductForCollect(String username) {
        List<ProductUser> productUsers = productUserRepository.findByUserId(userRepository.findByUserName(username).orElseThrow().getId());

        return productUsers.stream()
                .map(productUser -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setName(productUser.getProduct().getName());
                    productDTO.setMeasurementUnit(productUser.getProduct().getMeasurementUnit());
                    productDTO.setRemainder(productUser.getRemainder());
                    return productDTO;
                })
                .toList();
    }
}
