package com.example.demoseeder.seeder;

import com.example.demoseeder.entity.*;
import com.example.demoseeder.entity.enums.OrderSimpleStatus;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class OrderSeeder {
    public static List<Order> orders = new ArrayList<>();
    public static final int NUMBER_OF_ORDER = 1000;
    Faker faker = new Faker();
    public void save(){
        for (int i = 0; i < NUMBER_OF_ORDER; i++){
            int randomUserIndex = faker.number().numberBetween(0, UserSeeder.users.size() -1);
            User user = UserSeeder.users.get(randomUserIndex);

            Order order = new Order();
            order.setId(UUID.randomUUID().toString());
            order.setStatus(OrderSimpleStatus.DONE);
            order.setCreatedAt(LocalDateTime.now());
            order.setUserId(user.getId());
            Set<OrderDetail> orderDetails = new HashSet<>();
            HashMap<String, Product> mapProduct = new HashMap<>();
            int orderDetailNumber = faker.number().numberBetween(1, 5);
            for (int j = 0; j < orderDetailNumber; j++){
                int randomProductNumber = faker.number().numberBetween(0, ProductSeeder.products.size() - 1);
                Product product = ProductSeeder.products.get(randomProductNumber);
                if (mapProduct.containsKey(product.getId())){
                    continue;
                }
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(new OrderDetailId(order.getId(), product.getId()));
                orderDetail.setOrder(order);
                orderDetail.setProduct(product);
                orderDetail.setUnitPrice(product.getPrice());
                orderDetail.setQuantity(faker.number().numberBetween(1, 5));
                orderDetails.add(orderDetail);
                mapProduct.put(product.getId(), product);
            }
            order.setOrderDetails(orderDetails);
//            order.calculateTotalPrice();
            orders.add(order);
        }
    }
}
