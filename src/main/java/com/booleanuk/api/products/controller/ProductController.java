package com.booleanuk.api.products.controller;

import com.booleanuk.api.products.model.Product;
import com.booleanuk.api.products.model.ProductRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAll(@RequestBody String category) {
        if (category.isEmpty()) {
            return this.repository.findAll();
        }
        return this.repository.findAllBasedOnCategory(category);
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable int id) {
        Product product = repository.find(id);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return product;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct){
        if (repository.addProduct(newProduct) == null){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        } else {
            throw new ResponseStatusException(HttpStatus.CREATED);
        }
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product newProduct){
        return repository.updateProduct(id, newProduct);
    }

    @DeleteMapping("{id}")
    public boolean deleteProduct(@PathVariable int id){
        return repository.deleteProduct(id);
    }
}
