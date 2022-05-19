package com.example.shoppingcartdemo.entity.shoppingcart;

import com.example.shoppingcartdemo.util.AutoID;

public class CartItem {

    private int productId;
    private String productName;
    private String productThumbnail;
    private double unitPrice;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int productId, String productName, String productThumbnail, double unitPrice, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productThumbnail = productThumbnail;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }



    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productThumbnail='" + productThumbnail + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
