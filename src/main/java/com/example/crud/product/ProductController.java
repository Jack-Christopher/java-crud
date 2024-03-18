package com.example.crud.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping(path = "{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return this.productService.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<Object> newProduct(@RequestBody Product product) {
        return this.productService.newProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        return this.productService.newProduct(product);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        return this.productService.deleteProduct(id);
    }

}
