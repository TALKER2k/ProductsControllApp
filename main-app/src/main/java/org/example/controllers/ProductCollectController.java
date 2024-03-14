package org.example.controllers;

import jakarta.validation.Valid;
import org.example.DTO.CollectProductDTO;
import org.example.DTO.ProductDTO;
import org.example.services.ProductCollectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ProductCollectController {
    private final ProductCollectService productCollectService;

    public ProductCollectController(ProductCollectService userService) {
        this.productCollectService = userService;
    }

    @PostMapping("/collect_product")
    public ResponseEntity<String> createProduct(@RequestBody @Valid CollectProductDTO collectProductDTO) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            productCollectService.collectProduct(collectProductDTO, auth.getName());
            return ResponseEntity.ok()
                    .header("Server message", "User collected successfully")
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .header("Server message", "User collected unsuccessfully")
                    .body(ex.getMessage());
        }
    }

    @GetMapping("/check_my_collect")
    public List<ProductDTO> getMyCollectProducts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return productCollectService.getListProductForCollect(auth.getName());
    }
}
