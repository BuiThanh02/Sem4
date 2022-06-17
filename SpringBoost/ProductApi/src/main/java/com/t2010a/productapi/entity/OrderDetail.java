package com.t2010a.productapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orderdetail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", updatable = false, insertable = false, referencedColumnName = "id")
    @JsonBackReference
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("proId")
    @JoinColumn(name = "pro_id", updatable = false, insertable = false, referencedColumnName = "id")
    @JsonBackReference
    private Product product;
    private int qty;
    private BigDecimal unitPrice;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    private int status;
}
