package com.example.springbootassignment.api;

import com.example.springbootassignment.entity.Product;
import com.example.springbootassignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RequestMapping(path = "api/v1/products")
@RestController
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "limit", defaultValue = "10") int limit){
        return productService.findAllByActive(page, limit);
    }
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Product> findById(@PathVariable String id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

}
