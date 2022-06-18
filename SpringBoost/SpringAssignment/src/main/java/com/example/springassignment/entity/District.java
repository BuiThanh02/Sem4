package com.example.springassignment.entity;

import com.example.springassignment.entity.base.BaseEntity;
import com.example.springassignment.entity.enums.SimpleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "districts")
public class District extends BaseEntity {
    @Id
    private UUID id;
    private String name;
    private UUID cityId;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private City city;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Street> streets;
}
