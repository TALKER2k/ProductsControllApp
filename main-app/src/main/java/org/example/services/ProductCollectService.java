package org.example.services;

import org.example.DTO.CollectProductDTO;
import org.example.DTO.ProductDTO;

import java.util.List;

public interface ProductCollectService {
    void collectProduct(CollectProductDTO collectProductDTO, String username);
    List<ProductDTO> getListProductForCollect(String username);
}
