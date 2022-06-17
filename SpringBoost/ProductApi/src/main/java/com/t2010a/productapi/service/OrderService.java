package com.t2010a.productapi.service;

import com.t2010a.productapi.entity.Order;
import com.t2010a.productapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Iterable<Order> findAll(){
        Iterable<Order> listOrder = orderRepository.findAll();
        ArrayList<Order> validListOrder = new ArrayList<>();
        for (Order order: listOrder
        ) {
            if (order.getStatus() == 1){
                validListOrder.add(order);
            }
        }
        return validListOrder;
    }

    public Optional<Order> findById(UUID id){
        Optional<Order> foundOrder = orderRepository.findById(id);
        if (!foundOrder.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Order validOrder = foundOrder.get();
        if (validOrder.getStatus() == 1){
            return foundOrder;
        }else {
            return Optional.empty();
        }
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public void deleteById(UUID id){
        Optional<Order> deleteOrder = orderRepository.findById(id);
        if (!deleteOrder.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Order deletedOrder = deleteOrder.get();
        deletedOrder.setStatus(0);
        orderRepository.save(deletedOrder);
    }
}
