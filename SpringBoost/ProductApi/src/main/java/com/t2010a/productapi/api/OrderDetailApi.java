package com.t2010a.productapi.api;

import com.t2010a.productapi.entity.OrderDetail;
import com.t2010a.productapi.entity.User;
import com.t2010a.productapi.service.OrderDetailService;
import com.t2010a.productapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "api/v1/orderDetails")
@RestController
@CrossOrigin("*")
public class OrderDetailApi {
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<OrderDetail>> findAll(){
        return ResponseEntity.ok(orderDetailService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,path = "{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        Optional<OrderDetail> optionalOrderDetail = orderDetailService.findById(id);
        if (!optionalOrderDetail.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalOrderDetail.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderDetail> create(@RequestBody OrderDetail orderDetail){
        orderDetail.setId(UUID.randomUUID());
        if (!orderDetailService.findById(orderDetail.getId()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderDetailService.save(orderDetail));
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        if (!orderDetailService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        orderDetailService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<OrderDetail> update(@PathVariable UUID id, @RequestBody OrderDetail updateOrderDetail){
        Optional<OrderDetail> optionalOrderDetail = orderDetailService.findById(id);
        if (!optionalOrderDetail.isPresent()){
            ResponseEntity.badRequest().build();
        }
        OrderDetail existOrderDetail = optionalOrderDetail.get();

        existOrderDetail.setOrderId(updateOrderDetail.getOrderId());
        existOrderDetail.setUpdatedAt(updateOrderDetail.getUpdatedAt());
        existOrderDetail.setProId(updateOrderDetail.getProId());
        existOrderDetail.setCreatedAt(updateOrderDetail.getCreatedAt());
        existOrderDetail.setStatus(updateOrderDetail.getStatus());
        existOrderDetail.setQty(updateOrderDetail.getQty());
        existOrderDetail.setUnitPrice(updateOrderDetail.getUnitPrice());

        return ResponseEntity.ok(orderDetailService.save(existOrderDetail));
    }
}
