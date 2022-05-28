package com.example.assignment02.model;

import com.example.assignment02.entity.Product;

import java.util.List;

public interface ProductModel {
    Product save(Product product); // lưu thông tin.

    List<Product> findAll();

    Product findById(int id);

    Product update(int id, Product updateProduct);

    boolean delete(int id);
}
