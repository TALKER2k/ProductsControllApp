package org.example.services;

import org.example.DTO.PickProductDTO;
import org.example.DTO.ProductDTO;

import java.util.List;

public interface PickProductService {
    void pickProduct(PickProductDTO collectProductDTO, String username);

    List<ProductDTO> getListProductForPick(String username);
}
