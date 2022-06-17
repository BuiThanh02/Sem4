package com.t2010a.productapi.service;

import com.t2010a.productapi.entity.Product;
import com.t2010a.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll(){
        Iterable<Product> listProduct = productRepository.findAll();
        ArrayList<Product> validListProduct = new ArrayList<>();
        for (Product product: listProduct
             ) {
            if (product.getStatus() == 1){
                validListProduct.add(product);
            }
        }
        return validListProduct;
    }

    public Optional<Product> findById(UUID id){
        Optional<Product> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Product validProduct = foundProduct.get();
        if (validProduct.getStatus() == 1){
            return foundProduct;
        }else {
            return Optional.empty();
        }
    }

    public Product save(Product product){
        product.setId(UUID.randomUUID());
        return productRepository.save(product);
    }

    public void deleteById(UUID id){
        Optional<Product> deleteProduct = productRepository.findById(id);
        if (!deleteProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Product deletedProduct = deleteProduct.get();
        deletedProduct.setStatus(0);
        productRepository.save(deletedProduct);
    }
}
