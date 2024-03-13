package org.example.services.impl;

import org.example.models.HistoryCollectProduct;
import org.example.repositories.HistoryCollectProductRepository;
import org.example.services.HistoryProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryProductServiceImpl implements HistoryProductService {
    private final HistoryCollectProductRepository historyCollectProductRepository;

    public HistoryProductServiceImpl(HistoryCollectProductRepository historyCollectProductRepository) {
        this.historyCollectProductRepository = historyCollectProductRepository;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<HistoryCollectProduct> getInfoAtTime(Integer days) {
        return historyCollectProductRepository.findProductsCollectedAt(LocalDateTime.now().minusDays(days),
                LocalDateTime.now());
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<HistoryCollectProduct> getInfoAtTime(Integer days, Long id) {
        return historyCollectProductRepository.findProductsCollectedAt(LocalDateTime.now().minusDays(days),
                LocalDateTime.now()).stream()
                .filter(history -> history.getUser().getId().equals(id))
                .toList();
    }
}
