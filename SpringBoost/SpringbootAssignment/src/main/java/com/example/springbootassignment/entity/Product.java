package com.example.springbootassignment.entity;

import com.example.springbootassignment.entity.base.BaseEntity;
import com.example.springbootassignment.entity.myenum.ProductStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    @Lob
    private String detail; // text
    private String thumbnails;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

}
