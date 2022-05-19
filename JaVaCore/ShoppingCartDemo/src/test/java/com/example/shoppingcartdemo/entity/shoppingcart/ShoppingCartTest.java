package com.example.shoppingcartdemo.entity.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void add() {
        Product product1 = new Product(1, "ao", 10, "ao");
        Product product2 = new Product(2, "quan", 11, "quan");
        Product product3 = new Product(3, "giay", 12, "giay");
        Product product4 = new Product(4, "dep", 13, "dep");
        HashMap<String, CartItem> items = new HashMap<>();
        int quan = 1;
        ShoppingCart shoppingCart = new ShoppingCart(1, "thanh", "0979341091", "12TD", "it da", 10, items);
        shoppingCart.add(product1, quan);
        shoppingCart.add(product1, quan);
        shoppingCart.add(product2, quan);
        shoppingCart.add(product3, quan);
        shoppingCart.add(product4, quan);
    }

    @Test
    void update() {
        Product product1 = new Product(1, "ao", 10, "ao");
        Product product2 = new Product(2, "quan", 11, "quan");
        Product product3 = new Product(3, "giay", 12, "giay");
        Product product4 = new Product(4, "dep", 13, "dep");
        HashMap<String, CartItem> items = new HashMap<>();
        int quan = 1;
        int upQuan = 3;
        ShoppingCart shoppingCart = new ShoppingCart(1, "thanh", "0979341091", "12TD", "it da", 10, items);
        shoppingCart.add(product1, quan);
        shoppingCart.add(product2, quan);
        shoppingCart.add(product3, quan);
        shoppingCart.add(product4, quan);

        shoppingCart.update(upQuan,String.valueOf(product1.getId()));
        shoppingCart.update(upQuan,String.valueOf(product2.getId()));
        shoppingCart.update(upQuan,String.valueOf(product3.getId()));
        shoppingCart.update(upQuan,String.valueOf(product4.getId()));
        shoppingCart.update(upQuan,"6");
    }

    @Test
    void remove() {
        Product product1 = new Product(1, "ao", 10, "ao");
        Product product2 = new Product(2, "quan", 11, "quan");
        Product product3 = new Product(3, "giay", 12, "giay");
        Product product4 = new Product(4, "dep", 13, "dep");
        HashMap<String, CartItem> items = new HashMap<>();
        int quan = 1;
        ShoppingCart shoppingCart = new ShoppingCart(1, "thanh", "0979341091", "12TD", "it da", 10, items);
        shoppingCart.add(product1, quan);
        shoppingCart.add(product2, quan);
        shoppingCart.add(product3, quan);
        shoppingCart.add(product4, quan);

        shoppingCart.remove("1");
        shoppingCart.remove("2");
        shoppingCart.remove("3");
        shoppingCart.remove("4");
    }

    @Test
    void getListItems() {
        HashMap<String, CartItem> items = new HashMap<>();
        ShoppingCart shoppingCart = new ShoppingCart(1, "thanh", "0979341091", "12TD", "it da", 10, items);
        Product product1 = new Product(1, "ao", 10, "ao");
        Product product2 = new Product(2, "quan", 11, "quan");
        Product product3 = new Product(3, "giay", 12, "giay");
        Product product4 = new Product(4, "dep", 13, "dep");
        int quan =1;
        shoppingCart.add(product1, quan);
        shoppingCart.add(product1, quan);
        shoppingCart.add(product2, quan);
        shoppingCart.add(product3, quan);
        shoppingCart.add(product4, quan);
        for (CartItem cart:
                shoppingCart.getListItems()) {
            if (cart.getQuantity() != 0){
                System.out.println(cart.toString());
            }
        }
    }
}