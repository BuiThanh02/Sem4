package com.example.ead.seeder;

import com.example.ead.entity.Product;
import com.example.ead.repository.ProductRepository;
import com.example.ead.util.DateTimeRandom;
import com.github.javafaker.Faker;
import jdk.nashorn.internal.runtime.URIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProductSeeder {
    @Autowired
    ProductRepository productRepository;
    Faker faker = new Faker();
    public static List<Product> products = new ArrayList<>();
    public static final int NUMBER_OF_PRODUCT = 50;
    public void generate(){
        for (int i = 0; i < NUMBER_OF_PRODUCT; i++){
            Product product = new Product();
            product.setProdID(UUID.randomUUID().toString());
            product.setProdName(faker.name().fullName());
            product.setDescription(faker.lorem().sentence());
            product.setDateOfManf(DateTimeRandom.randomFull(1900, 2022));
            product.setPrice(new BigDecimal(faker.number().numberBetween(10, 200) * 10000));
            products.add(product);
        }
        productRepository.saveAll(products);
    }
}
