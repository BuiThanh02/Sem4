package com.example.demoseeder.entity;

import com.example.demoseeder.entity.base.BaseEntity;
import com.example.demoseeder.entity.enums.ProductSimpleStatus;
import com.example.demoseeder.entity.enums.ProductStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String description;
    private String detail; // text
    private String thumbnails; // nhiều ảnh cách nhau bởi dấu ,
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private ProductSimpleStatus status;
//    @Basic
//    private int status;
//    @Transient
//    private ProductStatus productStatus;
//
//    @PostLoad // khi load
//    void fillTransient() {
//        this.productStatus = ProductStatus.of(status); // chuyển dữ liệu dạng số ở trong database về kiểu enum
//    }
//
//    @PrePersist // trước khi lưu
//    void fillPersistent() {
//        // đưa giá trị của product status vào trường status.
//        this.status = this.productStatus.getValue();
//    }
}
