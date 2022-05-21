package com.example.productmanagementdemo.entity;

import java.time.LocalDateTime;

public class Car {
    private String Id;
    private String Name;
    private String Branch;
    private String Version;
    private double Price;
    private String Image;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
    private int Status;

    public Car() {
    }

    public Car(String id, String name, String branch, String version, double price, String image, LocalDateTime createdAt, LocalDateTime updatedAt, int status) {
        Id = id;
        Name = name;
        Branch = branch;
        Version = version;
        Price = price;
        Image = image;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        Status = status;
    }

    public Car(String id, String name, String branch, String version, double price, String image) {
        Id = id;
        Name = name;
        Branch = branch;
        Version = version;
        Price = price;
        Image = image;
        this.CreatedAt = LocalDateTime.now();
        this.UpdatedAt = LocalDateTime.now();
        this.Status = 1;
    }

    public Car(String id, String name, String branch, String version, double price, String image, LocalDateTime CreatedAt) {
        Id = id;
        Name = name;
        Branch = branch;
        Version = version;
        Price = price;
        Image = image;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = LocalDateTime.now();
        this.Status = 1;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        UpdatedAt = updatedAt;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Branch='" + Branch + '\'' +
                ", Version='" + Version + '\'' +
                ", Price=" + Price +
                ", Image='" + Image + '\'' +
                ", CreatedAt=" + CreatedAt +
                ", UpdatedAt=" + UpdatedAt +
                ", Status=" + Status +
                '}';
    }
}