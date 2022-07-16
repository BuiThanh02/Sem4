package com.example.springbootassignment.repository;

import com.example.springbootassignment.entity.Product;
import com.example.springbootassignment.entity.myenum.ProductStatus;
import com.example.springbootassignment.seeder.ProductSeeder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findAllByStatusEquals(ProductStatus status, Pageable pageable);

}
