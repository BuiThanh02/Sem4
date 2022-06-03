package com.t2010a.productapi.service;

import com.t2010a.productapi.entity.Product;
import com.t2010a.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.StubNotFoundException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(String proId){
        return productRepository.findById(proId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(String proId){
        productRepository.deleteById(proId);
    }

}
