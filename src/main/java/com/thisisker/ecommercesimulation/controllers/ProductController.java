package com.thisisker.ecommercesimulation.controllers;

import com.thisisker.ecommercesimulation.entities.Product;
import com.thisisker.ecommercesimulation.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/products")
    public Page<Product> getProducts(@RequestParam int page, @RequestParam int size) {
        return productService.getAllProducts(page, size);
    }
}
