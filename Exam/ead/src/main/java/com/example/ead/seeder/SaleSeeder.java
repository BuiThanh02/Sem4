package com.example.ead.seeder;

import com.example.ead.entity.Product;
import com.example.ead.entity.Sale;
import com.example.ead.repository.SaleRepository;
import com.example.ead.util.NumberUtil;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SaleSeeder {
    @Autowired
    SaleRepository saleRepository;

    Faker faker = new Faker();

    public static List<Sale> sales = new ArrayList<>();

    public static final int NUMBER_OF_SALE = 250;
    public void generate(){
        for (int i = 0; i < NUMBER_OF_SALE; i++){
            Sale sale = new Sale();
            int productIndex = NumberUtil.getRandomNumber(0, 50);
            Product product = ProductSeeder.products.get(productIndex);
            sale.setProduct(product);
            sale.setDOS(faker.name().fullName());
            sale.setSalesmanID(UUID.randomUUID().toString());
            sale.setSalesmanName(faker.name().fullName());
            sale.setSLNo(NumberUtil.getRandomNumber(1, 100));
            sales.add(sale);
        }
        saleRepository.saveAll(sales);
    }
}
