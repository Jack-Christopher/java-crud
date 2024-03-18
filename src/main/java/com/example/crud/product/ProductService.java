package com.example.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = this.productRepository.findProductByName(product.getName());

        HashMap<String, Object> data = new HashMap<>();

        if (res.isPresent() && product.getId() == null) {
            data.put("error", true);
            data.put("message", "A product with the same name already exists");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }

        data.put("product", product);
        data.put("message", "Product created successfully");
        if (product.getId() != null) {
            data.put("message", "Product updated successfully");
        }
        this.productRepository.save(product);

        return new ResponseEntity<>(
                data,
                HttpStatus.CREATED
        );
    }

    public Product getProduct(Long id) {
        Optional<Product> res = this.productRepository.findById(id);
        return res.orElse(null);
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
        boolean exists = this.productRepository.existsById(id);

        HashMap<String, Object> data = new HashMap<>();

        if (!exists) {
            data.put("error", true);
            data.put("message", "The product does not exist");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.NOT_FOUND
            );
        }

        data.put("message", "Product deleted successfully");
        this.productRepository.deleteById(id);

        return new ResponseEntity<>(
                data,
                HttpStatus.OK
        );
    }
}
