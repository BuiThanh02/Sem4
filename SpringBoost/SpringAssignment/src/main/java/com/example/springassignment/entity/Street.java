package com.example.springassignment.entity;

import com.example.springassignment.entity.base.BaseEntity;
import com.example.springassignment.entity.enums.SimpleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "streets")
public class Street extends BaseEntity {
    @Id
    private UUID id;
    private String name;
    private String description;

    private UUID districtId;
    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private District district;
    private SimpleEnum status;
}
