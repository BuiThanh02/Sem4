package com.example.ead.api;

import com.example.ead.entity.Product;
import com.example.ead.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(path = "api/v1/products")
@RestController
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
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
