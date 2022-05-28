package com.example.wcdexam.entity;

import com.example.wcdexam.entity.base.BaseEntity;
import com.example.wcdexam.entity.myenum.PhoneStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Phone extends BaseEntity {
    private int id;
    private int brandId;
    private String name;
    private double price;
    private String description;
    private PhoneStatus status;

    private HashMap<String, String> errors = new HashMap<>();

    public Phone() {
        this.name="";
        this.description="";

        this.price=0;

        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.status= PhoneStatus.ACTIVE;
    }

    public Phone(int id, int brandId, String name, double price, String description, PhoneStatus status, HashMap<String, String> errors) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.errors = errors;
    }
    public boolean isValid(){
        checkValidate();
        return errors.size() ==0;
    }

    private void checkValidate() {
        //validate du lieu theo kieu cui bap
        if (name == null || name.length() ==0) {
            errors.put("name", "Please enter name");
        }
        if (price == 0) {
            errors.put("price", "Please enter price");
        }
        if (description == null || description.length() == 0) {
            errors.put("image", "Please enter image");
        }
        if (description == null || description.length() == 0) {
            errors.put("description", "Please enter description");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhoneStatus getStatus() {
        return status;
    }

    public void setStatus(PhoneStatus status) {
        this.status = status;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", errors=" + errors +
                '}';
    }
}
