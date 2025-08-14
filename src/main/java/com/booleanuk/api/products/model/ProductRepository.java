package com.booleanuk.api.products.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public ProductRepository() {
        Product product1 = new Product(this.idCounter++, "car", "utilities", 10000);
        Product product2 = new Product(this.idCounter++, "house", "utilities", 200000);
        Product product3 = new Product(this.idCounter++, "beer", "drink", 5);
        this.data.add(product1);
        this.data.add(product2);
        this.data.add(product3);
    }

    public List<Product> findAll() {
        return this.data;
    }

    public List<Product> findAllBasedOnCategory(String category){
        return this.data.stream().filter(n -> n.getCategory().equals(category)).toList();
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product addProduct(Product product){
        this.data.add(product);
        return product;
    }

    public Product updateProduct(int id, Product newProduct){
        Product product = find(id);
        if (product == null){
            return null;
        }
        product.setName(newProduct.getName());
        product.setCategory(newProduct.getCategory());
        product.setPrice(newProduct.getPrice());
        return product;
    }

    public boolean deleteProduct(int id){
        return this.data.remove(data.get(id));
    }
}
