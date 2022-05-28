package com.example.asignment.model;

import com.example.asignment.entity.Product;

import java.util.List;

public interface ProductModel {
    Product save(Product product); // lưu thông tin.

    List<Product> findAll();

    Product findById(int id);

    Product update(int id, Product updateProduct);

    boolean delete(int id);
}
