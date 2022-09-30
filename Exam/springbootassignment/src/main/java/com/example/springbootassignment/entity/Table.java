package com.example.springbootassignment.entity;

import com.example.springbootassignment.entity.myenum.TableStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    private String id;
    private int number;
    private TableStatus status;
}
