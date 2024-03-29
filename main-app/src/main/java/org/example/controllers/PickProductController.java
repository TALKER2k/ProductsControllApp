package org.example.controllers;

import jakarta.validation.Valid;
import org.example.DTO.PickProductDTO;
import org.example.DTO.ProductDTO;
import org.example.services.PickProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class PickProductController {
    private final PickProductService pickProductService;

    public PickProductController(PickProductService pickProductService) {
        this.pickProductService = pickProductService;
    }

    @PostMapping("/pick_product")
    public ResponseEntity<String> createProduct(@RequestBody @Valid PickProductDTO pickProductDTO) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            pickProductService.pickProduct(pickProductDTO, auth.getName());
            return ResponseEntity.ok()
                    .header("Server message", "User picked successfully")
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .header("Server message", "User picked unsuccessfully")
                    .body(ex.getMessage());
        }
    }

    @GetMapping("/check_my_pick")
    public ResponseEntity<List<ProductDTO>> getMyPickProducts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok()
                .header("Server message", "Request all pick product succeeded")
                .body(pickProductService.getListProductForPick(auth.getName()));
    }
}
