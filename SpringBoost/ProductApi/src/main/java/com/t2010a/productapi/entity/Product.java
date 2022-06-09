package com.t2010a.productapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String proId;
    private String proName;
    private int proQty;
    private String thumbnail;
    private String proDescribe;
    private String proDetail;
    private double proPrice;
    private String makerDetail;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    private LocalDateTime deletedAt;
    private String userCreateDetail;
    private String userUpdateDetail;
    private String userDeleteDetail;
    private int status;
}
