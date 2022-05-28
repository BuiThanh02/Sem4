package com.example.wcdexam.entity;

import com.example.wcdexam.entity.base.BaseEntity;
import com.example.wcdexam.entity.myenum.BrandStatus;

import java.time.LocalDateTime;

public class Brand extends BaseEntity {
    private int id;//nen de String id UUID
    private  String name;
    private BrandStatus status;

    public
    Brand() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.status= BrandStatus.ACTIVE;
    }

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.status=BrandStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrandStatus getStatus() {
        return status;
    }

    public void setStatus(BrandStatus status) {
        this.status = status;
    }
}
