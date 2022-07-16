package com.example.ead.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    private String SalesmanID;
    private int SLNo;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ProdID")
    @JsonManagedReference
    private Product product;
    private String SalesmanName;
    private String DOS;
}
