package com.example.asignment.entity;

import com.example.asignment.entity.base.BaseEntity;
import com.example.asignment.entity.myenum.ProductStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product extends BaseEntity {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private String detail;
    private String thumbnail;
    private double price; // BigDecimal
    private ProductStatus status;

    private HashMap<String, String> errors = new HashMap<>();

    public boolean isValid() {
        checkValidate();
        return errors.size() == 0;
    }

    private void checkValidate() {
        // validate dữ liệu theo kiểu cùi bắp.
        if (name == null || name.length() == 0) {
            errors.put("name", "Please enter name");
        }
        if (price == 0) {
            errors.put("price", "Please enter price");
        }
        if (thumbnail == null || thumbnail.length() == 0) {
            errors.put("thumbnail", "Please choose thumbnail!");
        }
    }
    public void addErrors(String key, String value) {
        if(this.errors == null){
            this.errors = new HashMap<>();
        }
        this.errors.put(key, value);
    }

    public List<String> getListErrors() {
        return new ArrayList<>(errors.values());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", errors=" + errors +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public Product() {
        this.name = "";
        this.description = "";
        this.detail = "";
        this.thumbnail = "";
        this.price = 0;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.status = ProductStatus.ACTIVE;
    }

}
