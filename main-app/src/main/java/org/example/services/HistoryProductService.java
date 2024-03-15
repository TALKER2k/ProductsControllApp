package org.example.services;

import org.example.models.HistoryPickProduct;

import java.util.List;

public interface HistoryProductService {
    List<HistoryPickProduct> getInfoAtTime(Integer days);

    List<HistoryPickProduct> getInfoAtTime(Integer days, Integer id);
}
