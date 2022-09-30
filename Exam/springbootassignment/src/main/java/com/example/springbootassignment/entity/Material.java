package com.example.springbootassignment.entity;

import com.example.springbootassignment.entity.myenum.ProductStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "materials")
public class Material {
    @Id
    private String id;
    @Column(unique = true)
    private String name;
    private BigDecimal price;
    private String description;
    @Lob
    private String detail; // text
    private String thumbnails;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;
    private int qty;
}
