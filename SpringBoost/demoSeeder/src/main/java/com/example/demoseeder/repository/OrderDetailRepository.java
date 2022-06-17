package com.example.demoseeder.repository;

import com.example.demoseeder.entity.OrderDetail;
import com.example.demoseeder.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
