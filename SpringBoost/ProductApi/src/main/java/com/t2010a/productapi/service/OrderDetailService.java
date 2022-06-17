package com.t2010a.productapi.service;

import com.t2010a.productapi.entity.OrderDetail;
import com.t2010a.productapi.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Iterable<OrderDetail> findAll(){
        Iterable<OrderDetail> listOrderDetail = orderDetailRepository.findAll();
        ArrayList<OrderDetail> validListOrderDetail = new ArrayList<>();
        for (OrderDetail orderDetail: listOrderDetail
        ) {
            if (orderDetail.getStatus() == 1){
                validListOrderDetail.add(orderDetail);
            }
        }
        return validListOrderDetail;
    }

    public Optional<OrderDetail> findById(UUID id){
        Optional<OrderDetail> foundOrderDetail = orderDetailRepository.findById(id);
        if (!foundOrderDetail.isPresent()){
            ResponseEntity.badRequest().build();
        }
        OrderDetail validOrderDetail = foundOrderDetail.get();
        if (validOrderDetail.getStatus() == 1){
            return foundOrderDetail;
        }else {
            return Optional.empty();
        }
    }

    public OrderDetail save(OrderDetail orderDetail){
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteById(UUID id){
        Optional<OrderDetail> deleteOrderDetail = orderDetailRepository.findById(id);
        if (!deleteOrderDetail.isPresent()){
            ResponseEntity.badRequest().build();
        }
        OrderDetail deletedOrderDetail = deleteOrderDetail.get();
        deletedOrderDetail.setStatus(0);
        orderDetailRepository.save(deletedOrderDetail);
    }
}
