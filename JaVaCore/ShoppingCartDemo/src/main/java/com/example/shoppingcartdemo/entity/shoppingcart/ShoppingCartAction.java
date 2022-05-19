package com.example.shoppingcartdemo.entity.shoppingcart;

import java.util.ArrayList;

public interface ShoppingCartAction {
    void add(Product product, int quantity); // thêm số lượng sản phẩm vào cart.
    void update(int quantity, String id); // thay đổi số lượng của sản phẩm trong cart.
    void remove(String id); // remove sản phẩm khỏi cart.
    ArrayList<CartItem> getListItems();
}
