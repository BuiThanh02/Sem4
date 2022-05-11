package com.example.javacore_customers_demo.entity;

import java.time.LocalDateTime;

public class Customer {
    private String ID;
    private String Name;
    private String Phone;
    private String Image;
    private LocalDateTime DOB;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
    private int Status;

    public Customer(String ID, String name, String phone, String image, LocalDateTime DOB, LocalDateTime createdAt, LocalDateTime updatedAt, int status) {
        this.ID = ID;
        Name = name;
        Phone = phone;
        Image = image;
        this.DOB = DOB;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        Status = status;
    }

    public Customer() {
    }

    public Customer(String ID, String name, String phone, String image, LocalDateTime DOB) {
        this.ID = ID;
        Name = name;
        Phone = phone;
        Image = image;
        this.DOB = DOB;
        this.CreatedAt = LocalDateTime.now();
        this.UpdatedAt = LocalDateTime.now();
        this.Status = 1;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Image='" + Image + '\'' +
                ", DOB=" + DOB +
                ", CreatedAt=" + CreatedAt +
                ", UpdatedAt=" + UpdatedAt +
                ", Status=" + Status +
                '}';
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getImage() {
        return Image;
    }

    public LocalDateTime getDOB() {
        return DOB;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return UpdatedAt;
    }

    public int getStatus() {
        return Status;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setDOB(LocalDateTime DOB) {
        this.DOB = DOB;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        UpdatedAt = updatedAt;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
