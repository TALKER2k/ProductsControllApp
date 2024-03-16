package org.example.controllers;

import jakarta.validation.Valid;
import org.example.DTO.ProductDTO;
import org.example.models.HistoryPickProduct;
import org.example.models.Product;
import org.example.services.HistoryProductService;
import org.example.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final HistoryProductService historyProductService;
    private final ProductService productService;

    public ProductController(HistoryProductService historyProductService, ProductService productService) {
        this.historyProductService = historyProductService;
        this.productService = productService;
    }

    @GetMapping("/get_history")
    public ResponseEntity<List<HistoryPickProduct>> getHistoryPickProductAtTime(@RequestParam("days") Integer days) {
        return ResponseEntity.ok()
                .header("Server message", "Request history pick products by days succeeded")
                .body(historyProductService.getInfoAtTime(days));
    }

    @GetMapping("/get_history/{id}")
    public ResponseEntity<List<HistoryPickProduct>> getHistoryPickProductAtTimeById(@RequestParam("days") Integer days,
                                                                    @PathVariable Integer id) {
        return ResponseEntity.ok()
                .header("Server message", "Request history pick products by days and id succeeded")
                .body(historyProductService.getInfoAtTime(days, id));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addNewProduct(@RequestBody @Valid ProductDTO productDTO) {
        if (productService.existsByUserName(productDTO.getName())) {
            return ResponseEntity.badRequest()
                    .header("Server message", "Product is already exists")
                    .build();
        }

        Product product = productService.addProducts(productDTO);

        return ResponseEntity.ok()
                .header("Server message", "Product saved successfully")
                .body(product);
    }
}
