package com.t2010a.productapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private UUID id;
    private String name;
    private int status;
    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Product> products;
}
