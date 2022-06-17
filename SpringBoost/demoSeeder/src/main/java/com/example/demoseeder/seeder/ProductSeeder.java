package com.example.demoseeder.seeder;

import com.example.demoseeder.entity.Product;
import com.example.demoseeder.entity.enums.ProductSimpleStatus;
import com.example.demoseeder.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class ProductSeeder {
    @Autowired
    ProductRepository productRepository;
    Faker faker = new Faker();
    public static List<Product> products;
    public static final int NUMBER_OF_PRODUCT = 50;
    public void save(){
        for (int i = 0; i < NUMBER_OF_PRODUCT; i++){

            Product product = new Product();
            product.setId(UUID.randomUUID().toString());
            product.setName(String.valueOf(faker.leagueOfLegends()));
            product.setDescription(faker.lorem().sentence());
            product.setDetail(faker.lorem().sentence());
            product.setThumbnails(faker.avatar().image());
            product.setStatus(ProductSimpleStatus.ACTIVE);
            product.setPrice(new BigDecimal(faker.number().numberBetween(10, 200) * 10000));
            products.add(product);
        }
        productRepository.saveAll(products);
    }
}
