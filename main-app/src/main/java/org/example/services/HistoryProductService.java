package org.example.services;

import org.example.models.HistoryCollectProduct;

import java.util.List;

public interface HistoryProductService {
    List<HistoryCollectProduct> getInfoAtTime(Integer days);

    List<HistoryCollectProduct> getInfoAtTime(Integer days, Integer id);
}
