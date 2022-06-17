package com.t2010a.productapi.api;

import com.t2010a.productapi.entity.Order;
import com.t2010a.productapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "api/v1/orders")
@RestController
@CrossOrigin("*")
public class OrderApi {
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,path = "{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        Optional<Order> optionalOrder = orderService.findById(id);
        if (!optionalOrder.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalOrder.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> create(@RequestBody Order order){
        order.setId(UUID.randomUUID());
        if (!orderService.findById(order.getId()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.save(order));
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        if (!orderService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        orderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Order> update(@PathVariable UUID id, @RequestBody Order updateOrder){
        Optional<Order> optionalOrder = orderService.findById(id);
        if (!optionalOrder.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Order existOrder = optionalOrder.get();

        existOrder.setUpdatedAt(updateOrder.getUpdatedAt());
        existOrder.setCreatedAt(updateOrder.getCreatedAt());
        existOrder.setStatus(updateOrder.getStatus());
        existOrder.setNote(updateOrder.getNote());
        existOrder.setUserId(updateOrder.getUserId());
        existOrder.setTotalPrice(updateOrder.getTotalPrice());

        return ResponseEntity.ok(orderService.save(existOrder));
    }
}
