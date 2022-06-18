package com.example.springassignment.entity;

import com.example.springassignment.entity.base.BaseEntity;
import com.example.springassignment.entity.enums.SimpleEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "cities")
public class City extends BaseEntity {
    @Id
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<District> districts;
}
