package org.example.services.impl;

import org.example.DTO.PickProductDTO;
import org.example.DTO.ProductDTO;
import org.example.models.HistoryPickProduct;
import org.example.models.Product;
import org.example.models.UserTarget;
import org.example.models.User;
import org.example.repositories.HistoryPickProductRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.UserTargetRepository;
import org.example.repositories.UserRepository;
import org.example.services.PickProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PickProductServiceImpl implements PickProductService {
    private final HistoryPickProductRepository historyPickProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserTargetRepository userTargetRepository;

    public PickProductServiceImpl(HistoryPickProductRepository historyPickProductRepository, UserRepository userRepository, ProductRepository productRepository, UserTargetRepository userTargetRepository) {
        this.historyPickProductRepository = historyPickProductRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userTargetRepository = userTargetRepository;
    }

    @Override
    @Transactional
    public void pickProduct(PickProductDTO pickProductDTO, String username) {
        User userCollectedProduct = userRepository.findByUserName(username).orElseThrow();
        Product collectedProduct = productRepository.findByName(pickProductDTO.name()).orElseThrow();
        HistoryPickProduct historyPickProduct = new HistoryPickProduct()
                .setPick(pickProductDTO.pick())
                .setPickAt(LocalDateTime.now())
                .setUser(userCollectedProduct)
                .setProduct(collectedProduct);

        historyPickProductRepository.save(historyPickProduct);

        UserTarget userTarget = userTargetRepository.findByUserIdAndProductId(userCollectedProduct.getId(), collectedProduct.getId());
        userTarget.setRemainder(userTarget.getRemainder() - pickProductDTO.pick());
        userTargetRepository.save(userTarget);
    }

    @Override
    public List<ProductDTO> getListProductForPick(String username) {
        List<UserTarget> userTargets = userTargetRepository.findByUserId(userRepository.findByUserName(username).orElseThrow().getId());

        return userTargets.stream()
                .map(userTarget -> new ProductDTO()
                        .setName(userTarget.getProduct().getName())
                        .setMeasurementUnit(userTarget.getProduct().getMeasurementUnit())
                        .setRemainder(userTarget.getRemainder()))
                .toList();
    }
}
