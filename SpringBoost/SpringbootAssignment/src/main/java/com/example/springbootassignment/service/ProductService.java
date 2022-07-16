package com.example.springbootassignment.service;

import com.example.springbootassignment.entity.Product;
import com.example.springbootassignment.entity.myenum.ProductStatus;
import com.example.springbootassignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAll(){
        return productRepository.findAllByStatusEquals(ProductStatus.ACTIVE, PageRequest.of(0, 10));
    }

    public Optional<Product> findById(String id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(String id){
        productRepository.deleteById(id);
    }
}
