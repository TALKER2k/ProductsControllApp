package org.example.services.impl;

import org.example.models.HistoryPickProduct;
import org.example.repositories.HistoryPickProductRepository;
import org.example.services.HistoryProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryProductServiceImpl implements HistoryProductService {
    private final HistoryPickProductRepository historyPickProductRepository;

    public HistoryProductServiceImpl(HistoryPickProductRepository historyPickProductRepository) {
        this.historyPickProductRepository = historyPickProductRepository;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<HistoryPickProduct> getInfoAtTime(Integer days) {
        return historyPickProductRepository.findProductsCollectedAt(LocalDateTime.now().minusDays(days),
                LocalDateTime.now());
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<HistoryPickProduct> getInfoAtTime(Integer days, Integer id) {
        return historyPickProductRepository.findProductsCollectedAt(LocalDateTime.now().minusDays(days),
                        LocalDateTime.now()).stream()
                .filter(history -> history.getUser().getId().equals(id))
                .toList();
    }
}
