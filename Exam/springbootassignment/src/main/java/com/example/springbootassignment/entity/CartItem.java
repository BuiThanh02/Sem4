package com.example.springbootassignment.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Thông tin sản phẩm và số lượng sản phẩm,
 * kèm các thông tin giá tại thời điểm mua.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {
    @EmbeddedId
    private CartItemId id;
    private String productName; // đỡ truy vấn ngược
    private String productImage;
    private int quantity;
    private BigDecimal unitPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;
}
